package com.example.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by khangld5 on Apr 20, 2021
 */
@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {
}
