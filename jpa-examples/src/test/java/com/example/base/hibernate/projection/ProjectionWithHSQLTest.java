package com.example.base.hibernate.projection;

import com.example.base.BaseH2Test;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Created by khangld5 on Apr 27, 2021
 * note: em cant map tp interface-base projection
 */
class ProjectionWithHSQLTest extends BaseH2Test {

    @BeforeEach
    void setUp() {
        em.persistAndFlush(new Cafe("G7"));
    }

    @Test
    @SuppressWarnings({"deprecation", "unchecked"})
    void useTransformer() {
        List<MyCafeDTO> cafes = em.getEntityManager()
                .createQuery("SELECT c FROM CAFE c")
                .unwrap(Query.class)
                .setResultTransformer(Transformers.aliasToBean(MyCafeDTO.class))
                .getResultList();

        assertFalse(cafes.isEmpty());
    }

    @Test
    void useTuples() {
        List<Tuple> tuples = em.getEntityManager()
                .createQuery("SELECT c.id as id, c.name as title FROM CAFE c", Tuple.class)
                .getResultList();
        List<MyCafeDTO> cafes = tuples.stream().map(MyCafeDTO::new).collect(Collectors.toList());
        assertFalse(cafes.isEmpty());
    }

    @Test
    void useConstructorExpression() {
        List<MyCafeDTO> cafes = em.getEntityManager()
                .createQuery("SELECT new com.example.base.hibernate.projection.MyCafeDTO(c.id, c.name) FROM CAFE c", MyCafeDTO.class)
                .getResultList();
        assertFalse(cafes.isEmpty());
    }

    @Test
    void useConstructorResult() {
        List<MyCafeDTO> cafes = cafes = em.getEntityManager()
                .createNamedQuery("GetCafe", MyCafeDTO.class)
                .getResultList();
        assertFalse(cafes.isEmpty());
    }
}
