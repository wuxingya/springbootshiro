package com.example.service;

import com.example.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/30.
 */
public interface UserService {
    public User queryUser(Map<String,Integer> map);
    public PageInfo<User> queryUserAll(Map<String,Integer> map);
}
