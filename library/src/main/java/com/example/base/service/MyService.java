package com.example.base.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by khangld5 on Apr 07, 2021
 */
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(ServiceProperties.class)
public class MyService {

    private final @NonNull ServiceProperties serviceProperties;

    public String message() {
        return serviceProperties.getMessage();
    }
}
