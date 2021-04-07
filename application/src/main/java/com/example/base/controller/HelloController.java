package com.example.base.controller;

import com.example.base.service.MyService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by khangld5 on Apr 07, 2021
 */
@RestController
@RequiredArgsConstructor
class HelloController {

    private final @NonNull MyService myService;

    @GetMapping("/fun")
    public String home() {
        return myService.message();
    }
}
