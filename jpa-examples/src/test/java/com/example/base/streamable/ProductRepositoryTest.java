package com.example.base.streamable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    void test_findAllByDescriptionContaining() {
        assertNotNull(repository);

        repository.save(new Product(1, "new product"));
        Products products = repository.findAllByDescriptionContaining("new");
        assertFalse(products.isEmpty());
    }
}