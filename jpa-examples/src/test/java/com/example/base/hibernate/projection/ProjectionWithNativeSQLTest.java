package com.example.base.hibernate.projection;

import com.example.base.BaseH2Test;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertFalse;

class ProjectionWithNativeSQLTest extends BaseH2Test {

    @SuppressWarnings("all")
    private final String FIND_ALL_SQL = "SELECT c.id AS \"id\", c.name AS \"title\" FROM cafe c";

    @BeforeEach
    void setUp() {
        em.persistAndFlush(new Cafe("G7"));
    }

    /**
     * 3 Problems:
     * 1. Native Query need AS name with name wrap around quotations
     * 2. Mapping done via setter and therefore DTO must not be a value object
     * 3. Hibernate convert Long id -> int8 (bigint) but when it finds the setter in dto,
     * mapping looks for setId with type int8 = BigInteger -> ??
     * So using Long id in DTO will throw type mismatch error.
     */
    @Test
    @SuppressWarnings({"deprecation", "unchecked"})
    void useTransformer() {
        List<MyCafeDTO2> cafes = em.getEntityManager()
                .createNativeQuery(FIND_ALL_SQL)
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(MyCafeDTO2.class))
                .getResultList();

        assertFalse(cafes.isEmpty());
    }

    @Test
    @SuppressWarnings("unchecked")
    void useConstructorResult() {
        List<MyCafeDTO> cafes = em.getEntityManager()
                .createNativeQuery(FIND_ALL_SQL, "MyCafeDTOMapping")
                .getResultList();
        assertFalse(cafes.isEmpty());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyCafeDTO2 {
        private BigInteger id;
        private String title;
    }

}
