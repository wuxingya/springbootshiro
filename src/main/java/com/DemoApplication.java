package com;

import com.example.demo.service.TestServer;
import com.example.model.User;
import com.example.service.TestService;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;

import org.mybatis.spring.annotation.MapperScan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;



@SpringBootApplication
@Controller
@MapperScan("com.example.mapper")
@EnableScheduling
public class DemoApplication {
    Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    @Autowired
    TestServer testServer;
    @Autowired
    com.example.xing.WuService wuService;
    @Autowired
    com.wu.WuService iWuService;
    @Autowired
    UserService userService;
    @Autowired
    TestService testService;
    @RequestMapping("/")
    public String hello(Map<String, Object> map, HttpServletRequest request) {

//		map.put("name", "[Angel -- 守护天使]");
        map.put("name", request.getParameter("name"));
        testServer.Test();
        wuService.Test();
        iWuService.Test();
        Integer id =testService.queryId(100);
        logger.info("数据库2 的数据testid ={}",id);
        Map<String, Integer> parammap = new HashMap<>();
        parammap.put("id", 100);
        parammap.put("newId", 1402846);
        User user = userService.queryUser(parammap);
        logger.info(user.toString());
        logger.info(""+user.getUserId());
        logger.info(""+user.getBlance());
        logger.info(""+user.getDate());
        PageInfo<User> pageInfo = userService.queryUserAll(parammap);
        map.put("pageInfo", pageInfo);
        map.put("list", pageInfo.getList());
        logger.info("你好{}","asdf");

        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
