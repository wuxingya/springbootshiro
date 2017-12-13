package com.Config;

import com.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/12.
 */
@Configuration
public class DynamicDataSourceConfig {

    @Autowired
    @Qualifier("dataSource1")
    private DataSource ds1;
    @Autowired
    @Qualifier("dataSource2")
    private DataSource ds2;
    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(ds1);
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("ds1", ds1);
        dsMap.put("ds2", ds2);

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }
}
