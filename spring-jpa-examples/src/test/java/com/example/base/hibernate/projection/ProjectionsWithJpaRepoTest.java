package com.example.base.hibernate.projection;

import com.example.base.BaseJpaH2Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ProjectionsWithJpaRepoTest extends BaseJpaH2Test {
    @Autowired
    CafeRepository cafeRepository;

    @BeforeEach
    void setUp() {
        cafeRepository.save(new Cafe("G7"));
    }

    @Test
    void queryWithInterfaceProjection() {
        List<MyCafe> cafes = cafeRepository.findEveryThingInterfaceProjection();

        assertFalse(cafes.isEmpty());
    }


    @Test
    void nativeQueryWithInterfaceProjection() {
        List<MyCafe> cafes = cafeRepository.findEveryThingNativeQueryInterfaceProjection();

        assertFalse(cafes.isEmpty());
    }

    @Test
    void queryWithClassProjection() {
        List<MyCafeDTO> cafes = cafeRepository.findEveryThingClassProjection();

        assertFalse(cafes.isEmpty());
    }

    @Test
    void nativeQueryWithClassProjection() {
        List<MyCafeDTO> cafes = cafeRepository.getCafes();

        assertFalse(cafes.isEmpty());
    }

    @Test
    void dynamicProjection() {

    }
}
