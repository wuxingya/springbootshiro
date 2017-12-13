package com.Config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/12/12.
 * 默认这个com.example.mapper.User包下的所有Mapper.class 都是用 sqlSessionFactory1
 */
@Configuration
@MapperScan(basePackages = "com.example.mapper.User", sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    static final String MAPPER_LOCATION = "classpath:mapping/*.xml";
    @Autowired
    Environment environment;

    @Bean(name = "dataSource1")     //声明其为Bean实例
//    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource1() {
        //获取日志的另外一种方式
        logger.info("environment={}", environment.getProperty("spring.datasource1.url"));
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(environment.getProperty("spring.datasource1.url"));
        datasource.setUsername(environment.getProperty("spring.datasource1.username"));
        datasource.setPassword(environment.getProperty("spring.datasource1.password"));
        datasource.setDriverClassName(environment.getProperty("spring.datasource1.driver-class-name"));

        //configuration
        datasource.setInitialSize(environment.getProperty("spring.datasource1.initialSize", Integer.class));
        datasource.setMinIdle(environment.getProperty("spring.datasource1.minIdle", Integer.class));
        datasource.setMaxActive(environment.getProperty("spring.datasource1.maxActive", Integer.class));
        datasource.setMaxWait(environment.getProperty("spring.datasource1.maxWait", Integer.class));
        datasource.setTimeBetweenEvictionRunsMillis(environment.getProperty("spring.datasource1.timeBetweenEvictionRunsMillis", Integer.class));
        datasource.setMinEvictableIdleTimeMillis(environment.getProperty("spring.datasource1.minEvictableIdleTimeMillis", Integer.class));
        datasource.setValidationQuery(environment.getProperty("spring.datasource1.validationQuery"));
        datasource.setTestWhileIdle(environment.getProperty("spring.datasource1.testWhileIdle", Boolean.class));
        datasource.setTestOnBorrow(environment.getProperty("spring.datasource1.testOnBorrow", Boolean.class));
        datasource.setTestOnReturn(environment.getProperty("spring.datasource1.testOnReturn", Boolean.class));
        datasource.setPoolPreparedStatements(environment.getProperty("spring.datasource1.poolPreparedStatements", Boolean.class));
        try {
            datasource.setFilters(environment.getProperty("spring.datasource1.filters"));
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(environment.getProperty("spring.datasource1.connectionProperties"));

        return datasource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") DataSource dataSource1) {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        try {
            factory.setDataSource(dataSource1);
            //SqlSessionFactory 加入mapping xml的映射文件 执行时找不到对应的xml和方法
            factory.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources(DataSourceConfig.MAPPER_LOCATION));
            return factory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory1) {

        return new SqlSessionTemplate(sqlSessionFactory1);
    }

}
