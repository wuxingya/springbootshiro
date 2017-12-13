package com.aop;

import com.datasource.DataSource;
import com.datasource.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/12/12.
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class MpAop {
    @Pointcut("execution(* com.example.service..*.*(..))")
    public void aspect() {}

    @Before("aspect()")
    public void MyBefore(JoinPoint pjp){
        Object object =pjp.getTarget();
        Signature signature= pjp.getSignature();
        log.info("前置通知");
        log.info(object.toString());
        log.info("pjp.getSignature()="+pjp.getSignature());
        log.info("pjp.getTarget().getClass()="+pjp.getTarget().getClass());
        MethodSignature proxySignature = (MethodSignature) pjp.getSignature();
        Method proxyMethod = proxySignature.getMethod();
        Class<?> target = pjp.getTarget().getClass();
        Method targetMethod = null;
        try {
            targetMethod = target.getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (targetMethod != null) {
            Transactional transactional = target.getAnnotation(Transactional.class);
            if (transactional != null) {
                //获取目标类 DataSource注解
                DataSource clazzDatasource = target.getAnnotation(DataSource.class);
                //获取目标方法 DataSource注解
                DataSource methodDatasource = targetMethod.getAnnotation(DataSource.class);
                log.info("方法名为：{}",targetMethod.getName());
                //注解到类的数据源为
                if (methodDatasource != null) {
                    log.debug("using specify method datasource({}) ......", methodDatasource.value());
                    DataSourceContextHolder.setDB(methodDatasource.value());
                    log.info("注解到方法的数据源为{}",methodDatasource.value());

                } else if (clazzDatasource != null) {
                    //注解到方法上的数据源为
                    log.debug("using specify clazz datasource({}) ......", clazzDatasource.value());
                    DataSourceContextHolder.setDB(clazzDatasource.value());
                    log.info("注解到类上的数据源为{}",clazzDatasource.value());
                } else {
                    log.warn("does not specify any datasource, using default datasource ......");
                }
            } else {
                log.debug("non transactional method, using default datasource({}) ......");
            }
        }

    }
    @Around("aspect()")
    public  Object MyAround(ProceedingJoinPoint proceedingJoinPoint){
        log.info("环绕通知start");
        Object result = null;
        try {
            Signature signature=  proceedingJoinPoint.getSignature();
            log.info("signature.toString(){}"+signature.toString());
            log.info("getModifiers{}",signature.getModifiers());
            log.info("getName{}",signature.getName());
            log.info("getDeclaringType{}",signature.getDeclaringType());
            log.info("getDeclaringTypeName{}",signature.getDeclaringTypeName());
            result=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("环绕通知end");
        return result;
    }
    @After("aspect()")
    public void MyAfter(JoinPoint joinPoint){
        log.info("JoinPoint joinPoint{}", joinPoint);
        DataSourceContextHolder.clearDB();
    }
}
