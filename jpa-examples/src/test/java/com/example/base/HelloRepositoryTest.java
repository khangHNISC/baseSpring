package com.example.base;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=validate"})
class HelloRepositoryTest {

    @Autowired
    private HelloRepository helloRepository;

    @Test
    void findById() {
        //is this one go to db permanent or new method has new instance
        helloRepository.save(new Hello(1L));
        Optional<Hello> h = helloRepository.findById(1L);
        assertTrue(h.isPresent());
    }
}