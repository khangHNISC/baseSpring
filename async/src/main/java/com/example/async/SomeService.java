package com.example.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by khangld5 on May 18, 2021
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SomeService {

    private final AsyncStuff asyncStuff;

    public String compute() throws InterruptedException {
        asyncStuff.pushNoti();
        return "hello";
    }
}