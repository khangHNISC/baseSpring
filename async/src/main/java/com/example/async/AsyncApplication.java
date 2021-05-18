package com.example.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
public class AsyncApplication {

    @Autowired
    SomeService service;

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("testThread-");
        executor.initialize();
        return executor;
    }

    @GetMapping("/")
    public String hello() throws InterruptedException {
        return service.compute();
    }
}
