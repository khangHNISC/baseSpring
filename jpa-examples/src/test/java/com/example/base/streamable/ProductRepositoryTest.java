package com.example.base.streamable;

import com.example.base.BaseH2Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductRepositoryTest extends BaseH2Test {

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