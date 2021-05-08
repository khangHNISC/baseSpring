package com.example.helloapp;

import hello.HelloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloCommandLineRunner implements CommandLineRunner {

    private final HelloService service;

    public HelloCommandLineRunner(HelloService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        service.sayHello("World");
    }
}
