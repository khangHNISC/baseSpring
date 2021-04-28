package com.example.base;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class HelloRepositoryTest {

    @Autowired
    private HelloRepository helloRepository;

    @Test
    void findById() {
        helloRepository.save(new Hello(1L));
        Optional<Hello> h = helloRepository.findById(1L);
        assertTrue(h.isPresent());
    }
}