package com.example.jooqexmaples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by khangld5 on Apr 29, 2021
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TestJOOQ {
    @Test
    void setUp() {
        System.out.println("123");
    }
}
