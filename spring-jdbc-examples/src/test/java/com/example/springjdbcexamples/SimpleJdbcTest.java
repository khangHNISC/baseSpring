package com.example.springjdbcexamples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://stackoverflow.com/questions/50371775/why-does-spring-data-jdbc-not-save-my-car-object
 */
class SimpleJdbcTest extends BaseJdbcH2Test {

    @Autowired
    PetRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(Pet.create(null, "Kien", LocalDate.of(2020, 4, 30)));
        repository.save(Pet.create(null, "Kien2", LocalDate.of(2021, 4, 30)));
    }

    @Test
    void isSaved() {
        assertTrue(repository.findAll().iterator().hasNext());
    }
}
