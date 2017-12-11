package com.example.xing;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/29.
 */
@Slf4j
@Service("wuService")
public class WuService {
    Logger logger = LoggerFactory.getLogger(WuService.class);
    public void Test(){
        logger.debug("这是输出到另一个文件的日志");
        logger.info("这是输出到另一个文件的日志");
        log.info("有什么不一样！没什么不一样");
        log.debug("有什么不一样！没什么不一样");
    }
}
