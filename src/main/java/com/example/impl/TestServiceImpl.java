package com.example.impl;

import com.datasource.DataSource;
import com.example.mapper.Test.TestMapper;
import com.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/12/13.
 */
@Transactional
@Service
@DataSource(value=DataSource.slave)
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;
    @Override
    public Integer queryId(Integer id) {
        return testMapper.queryId(id);
    }
}
