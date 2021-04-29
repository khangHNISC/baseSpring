package com.example.base.streamable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends JpaRepository<Product, Long> {
    Products findAllByDescriptionContaining(String text);
}
