package com.example.securedapplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller {

    @GetMapping("/private")
    public String hello() {
        return "hello";
    }
}
