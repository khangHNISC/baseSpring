package com.example.springsessionexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureWebTestClient
public abstract class AbstractRedisTestContainer {

    @Container
    protected static final GenericContainer<?> redisContainer =
            new GenericContainer<>("redis:6-alpine").withExposedPorts(6379);

    @Autowired
    protected WebTestClient testClient;

    @DynamicPropertySource
    @SuppressWarnings("unused")
    static void properties(DynamicPropertyRegistry r) {
        r.add("spring.redis.host", redisContainer::getContainerIpAddress);
        r.add("spring.redis.port", redisContainer::getFirstMappedPort);
    }
}
