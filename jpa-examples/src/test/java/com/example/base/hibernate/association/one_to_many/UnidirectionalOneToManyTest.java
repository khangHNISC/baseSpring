package com.example.base.hibernate.association.one_to_many;

import com.example.base.BaseH2Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by khangld5 on Apr 23, 2021
 */
class UnidirectionalOneToManyTest extends BaseH2Test {
    private Employee admin;

    @BeforeEach
    void setup() {
        admin = Employee.builder().name("kien").build();
        Role adminRole = Role.builder().name("admin").build();
        Role presidentRole = Role.builder().name("president").build();
        admin.addRole(adminRole);
        admin.addRole(presidentRole);
    }

    @Test
    void testAdd() {
        em.persistAndFlush(admin);
        Employee saved = em.getEntityManager().createQuery("SELECT e FROM " +
                "UnidirectionalOneToManyTest$Employee e join fetch e.roles WHERE e.id = :id", Employee.class)
                .setParameter("id", 1L)
                .getSingleResult();
        assertFalse(saved.getRoles().isEmpty());
    }

    @Test
    void testRemoveParent() {
        Employee saved = em.persistFlushFind(admin);
        em.remove(saved);
        em.flush();

        List<Role> roles = em.getEntityManager()
                .createQuery("SELECT r FROM UnidirectionalOneToManyTest$Role r", Role.class)
                .getResultList();
        assertTrue(roles.isEmpty());
    }

    @Test
    void allowEmptyChild() {
        admin = Employee.builder().name("kien").build();
        em.persistAndFlush(admin);
    }

    @Builder
    @Entity(name = "UnidirectionalOneToManyTest$Employee")
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Employee {
        @Id
        @GeneratedValue
        long id;

        String name;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        //create employee_roles table auto 
        private final List<Role> roles = new ArrayList<>();

        public List<Role> getRoles() {
            return Collections.unmodifiableList(roles);
        }

        public void addRole(Role newRole) {
            roles.add(newRole);
        }
    }

    @Builder
    @Entity(name = "UnidirectionalOneToManyTest$Role")
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Role {
        @Id
        @GeneratedValue
        long id;

        String name;
    }
}
