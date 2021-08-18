package com.example.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by khangld5 on May 18, 2021
 */
@Slf4j
@Service
public class AsyncStuff {
    @Async
    public void pushNoti() throws InterruptedException {
        log.info("im running");
        Thread.sleep(10000);
        log.info("im done");
    }
}
