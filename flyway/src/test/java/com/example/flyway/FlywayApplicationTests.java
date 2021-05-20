package com.example.flyway;

import com.example.flyway.core.Cup;
import com.example.flyway.core.CupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class FlywayApplicationTests {

    @Autowired
    CupRepository cupRepository;

    @Test
    void contextLoads() {
        List<Cup> cups = cupRepository.findAll();
        assertFalse(cups.isEmpty());
    }
}
