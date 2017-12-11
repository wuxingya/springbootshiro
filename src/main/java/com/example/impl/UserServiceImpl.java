package com.example.impl;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User queryUser(Map<String,Integer> map) {
        userMapper.updateUser(map);
        return userMapper.queryUser(map.get("newId"));
    }

    @Override
    public PageInfo<User> queryUserAll(Map<String, Integer> map) {
        Page p =PageHelper.startPage(1,10);
        List<User> list= userMapper.queryUserAll(map);
        p.getResult();
        p.getTotal();
        PageInfo<User> pageInfo   = new PageInfo(list);
        System.out.println(pageInfo);
        return pageInfo;
    }
}
