package com.example.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(scanBasePackages = "com.example.base")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
