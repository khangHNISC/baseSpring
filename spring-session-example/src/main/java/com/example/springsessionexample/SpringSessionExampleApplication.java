package com.example.springsessionexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

@RestController
@SpringBootApplication
public class SpringSessionExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSessionExampleApplication.class, args);
    }

    @GetMapping(path = "/")
    public String home(WebSession session) {
        return session.getId();
    }
}
