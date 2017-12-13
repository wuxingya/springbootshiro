package com.Config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/12/12.
 */
@Configuration
@MapperScan(basePackages="com.example.mapper.Test",sqlSessionFactoryRef="sqlSessionFactory2")
public class DataSourceConfig2 {
    private Logger logger = LoggerFactory.getLogger(DataSourceConfig2.class);
    static final String MAPPER_LOCATION = "classpath:mapping/*.xml";
    @Value("${spring.datasource2.url}")
    private String dbUrl;

    @Value("${spring.datasource2.username}")
    private String username;

    @Value("${spring.datasource2.password}")
    private String password;

    @Value("${spring.datasource2.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource2.initialSize}")
    private int initialSize;

    @Value("${spring.datasource2.minIdle}")
    private int minIdle;

    @Value("${spring.datasource2.maxActive}")
    private int maxActive;

    @Value("${spring.datasource2.maxWait}")
    private int maxWait;

    @Value("${spring.datasource2.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource2.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource2.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource2.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource2.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource2.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource2.poolPreparedStatements}")
    private boolean poolPreparedStatements;


    @Value("${spring.datasource2.filters}")
    private String filters;

    @Value("{spring.datasource2.connectionProperties}")
    private String connectionProperties;

    @Bean(name="dataSource2")     //声明其为Bean实例
//    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource2(){
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
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2")DataSource dataSource2)  {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        try {
            factory.setDataSource(dataSource2);
            factory.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources(DataSourceConfig.MAPPER_LOCATION));
            return factory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2")SqlSessionFactory sqlSessionFactory2) {
        return new SqlSessionTemplate(sqlSessionFactory2);
    }

}
