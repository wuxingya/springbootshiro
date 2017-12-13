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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/12/12.
 */
@Configuration
@MapperScan(basePackages="com.example.mapper.User",sqlSessionFactoryRef="sqlSessionFactory1")
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    static final String MAPPER_LOCATION = "classpath:mapping/*.xml";
    @Autowired
    Environment environment;
    @Value("${spring.datasource1.url}")
    private String dbUrl;

    @Value("${spring.datasource1.username}")
    private String username;

    @Value("${spring.datasource1.password}")
    private String password;

    @Value("${spring.datasource1.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource1.initialSize}")
    private int initialSize;

    @Value("${spring.datasource1.minIdle}")
    private int minIdle;

    @Value("${spring.datasource1.maxActive}")
    private int maxActive;

    @Value("${spring.datasource1.maxWait}")
    private int maxWait;

    @Value("${spring.datasource1.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource1.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource1.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource1.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource1.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource1.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource1.poolPreparedStatements}")
    private boolean poolPreparedStatements;


    @Value("${spring.datasource1.filters}")
    private String filters;

    @Value("{spring.datasource1.connectionProperties}")
    private String connectionProperties;

    @Bean(name="dataSource1")     //声明其为Bean实例
//    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource1(){
        //获取日志的另外一种方式
        logger.info("environment={}",environment.getProperty("spring.datasource1.url"));
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1")DataSource dataSource1) {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        try {
            factory.setDataSource(dataSource1);
            factory.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources(DataSourceConfig.MAPPER_LOCATION));
            return factory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory1")SqlSessionFactory sqlSessionFactory1) {

        return new SqlSessionTemplate(sqlSessionFactory1);
    }

}
