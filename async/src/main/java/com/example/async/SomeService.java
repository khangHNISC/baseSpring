package com.example.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by khangld5 on May 18, 2021
 */
@Service
public class SomeService {

    @Async
    public String compute() throws InterruptedException {
        pushNoti();
        pushNoti2();
        return "hello";
    }

    @Async
    public void pushNoti() throws InterruptedException {
        System.out.println("im running");
        Thread.sleep(10000);
    }

    @Async
    public void pushNoti2() throws InterruptedException {
        System.out.println("im running2");
        Thread.sleep(10000);
    }
}
