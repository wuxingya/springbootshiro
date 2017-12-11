package com.example.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.One;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/12/7.
 */
@Slf4j
@Component
public class Jobs {
    public final static long ONE_Minute =  60 * 1000;
    public SimpleDateFormat  sf= new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){

        log.info(sf.format(new Date())+" >>fixedDelay执行....");
        new TreandTest().start();
    }

    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob(){
        log.info(sf.format(new Date())+" >>fixedRate执行....");
        new TreandTest().start();
    }

    @Scheduled(cron="5 * * * * ?")
    public void cronJob(){
        log.info(sf.format(new Date())+" >>cron执行....");
        new TreandTest().start();
    }


}
