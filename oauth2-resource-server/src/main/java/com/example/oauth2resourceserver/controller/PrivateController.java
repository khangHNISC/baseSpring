package com.example.oauth2resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by khangld5 on May 04, 2021
 */
@RestController
public class PrivateController {

    @GetMapping("/messages")
    public String[] getMessages() {
        return new String[]{"Message 1", "Message 2", "Message 3"};
    }
}
