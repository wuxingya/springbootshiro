package com.example.scheduled;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2017/12/7.
 */
@Slf4j
public class TreandTest extends Thread {
    @Override
    public void run() {
        log.info(super.getName()+"开始了");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(super.getName()+"结束了");
    }
}
