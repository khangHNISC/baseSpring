package com.example.base.hibernate.association.one_to_many;

import com.example.base.BaseJpaH2Test;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class BidirectionalOneToManyTest extends BaseJpaH2Test {

    private Employee admin;

    @BeforeEach
    void setup() {
        admin = new Employee("kien");
        Role adminRole = new Role("admin", admin);
        Role presidentRole = new Role("president", admin);
        admin.addRole(adminRole);
        admin.addRole(presidentRole);
    }

    @Test
    void testAdd() {
        Employee saved = em.persistAndFlush(admin);
        assertFalse(saved.getRoles().isEmpty());
    }

    @Test
    void addRoleWithNullEmployee() {
        Role adminRole = em.persistFlushFind(new Role("admin"));
        assertNull(adminRole.employee);
    }

    @Entity
    @NoArgsConstructor
    private static class Employee {
        @Id
        @GeneratedValue
        private Long id;

        String name;

        //if the collection is huge then consider only keep @ManyToOne at child then using query to get fetch
        //This collection cannot limit size like query-level pagination
        //this might loose nice function like cascade or orphan but gain performance
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
        private final Set<Role> roles = new HashSet<>();

        public Employee(String name) {
            this.name = name;
        }

        public Set<Role> getRoles() {
            return Collections.unmodifiableSet(roles);
        }

        public void addRole(Role newRole) {
            roles.add(newRole);
        }
    }

    @Entity
    @NoArgsConstructor
    private static class Role {
        @Id
        @GeneratedValue
        private Long id;

        String name;

        @ManyToOne(fetch = FetchType.LAZY) //this properly all you need
        Employee employee;

        public Role(String name, Employee employee) {
            this.name = name;
            this.employee = employee;
        }

        public Role(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Role)) {
                return false;
            }
            Role other = (Role) obj;
            return id != null && id.equals(other.id);
        }

        @Override
        public int hashCode() {
            return getClass().hashCode();
        }
    }
}
