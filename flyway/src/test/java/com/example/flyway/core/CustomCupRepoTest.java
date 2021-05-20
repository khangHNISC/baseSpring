package com.example.flyway.core;

import com.example.flyway.configs.CoreEMConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({CoreEMConfig.class, CustomCupRepo.class})
class CustomCupRepoTest {

    @Autowired
    CustomCupRepo cupRepository;

    @Test
    void saveSthOutsidePackage() {
        assertThrows(IllegalArgumentException.class, () -> cupRepository.saveCustomer());
    }
}