package com.example.mapper.User;

import com.example.model.User;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/30.
 */
@Component
public interface UserMapper {
    public User queryUser(Integer id);
    public void updateUser(Map<String,Integer> map);
    List<User> queryUserAll(Map<String,Integer> map);
}
