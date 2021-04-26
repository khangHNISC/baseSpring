package com.example.base.hibernate.association.one_to_many;

import com.example.base.BaseH2Test;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.LazyInitializationException;
import org.hibernate.annotations.NaturalId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by khangld5 on Apr 23, 2021
 */
class UnidirectionalOneToManyTest extends BaseH2Test {

    @Autowired
    EmployeeRepository repository;

    private Employee admin;

    @BeforeEach
    void setup() {
        admin = new Employee("kien");
        Role adminRole = new Role("admin");
        Role presidentRole = new Role("president");
        admin.addRole(adminRole);
        admin.addRole(presidentRole);
    }

    @Test
    void testAdd() {
        em.persistAndFlush(admin);
        //these tests auto fetch lazy relation since all of this test is under transaction
        Employee saved = em.getEntityManager().createQuery("SELECT e FROM " +
                "UnidirectionalOneToManyTest$Employee e WHERE e.id = :id", Employee.class)
                .setParameter("id", 1L)
                .getSingleResult();
        assertFalse(saved.getRoles().isEmpty());
    }

    @Test
    void testCascadeDelete() {
        Employee saved = em.persistAndFlush(admin);
        em.remove(saved);
        em.flush();

        List<Role> roles = em.getEntityManager()
                .createQuery("SELECT r FROM UnidirectionalOneToManyTest$Role r where r.owner = :owner", Role.class)
                .setParameter("owner", saved.id)
                .getResultList();
        assertTrue(roles.isEmpty());
    }

    @Test
    void orphanRemoval() {
        long roleId = em.persistAndFlush(admin).removeFirstRole();
        em.flush();

        assertNull(em.find(Role.class, roleId));
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void isFuckingLazyBro() {
        Employee saved = repository.save(admin);
        Optional<Set<Role>> getSaveByFind = repository.findById(saved.id).map(Employee::getRoles);
        assertThrows(LazyInitializationException.class, () -> getSaveByFind.map(Set::size));
    }

    @Entity(name = "UnidirectionalOneToManyTest$Employee")
    @NoArgsConstructor
    private static class Employee {
        @Id
        @GeneratedValue
        long id;

        String name;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
        @JoinColumn(name = "owner", referencedColumnName = "id")
        //if turn off create extra employee_roles table like many to many
        //set over list
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

        public long removeFirstRole() {
            Role r = roles.iterator().next();
            roles.remove(r);
            return r.id;
        }

        public int hashCode() {
            return getClass().hashCode();
        }
    }

    @Entity(name = "UnidirectionalOneToManyTest$Role")
    @NoArgsConstructor
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    private static class Role {
        @Id
        @GeneratedValue
        long id;

        @NaturalId
        @EqualsAndHashCode.Include
        String name;

        /**
         * 1. this one must be null see this
         * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
         * <p>
         * 2. when delete trigger a null update on this field before delete
         * need this insertable and updatable for cascade delete
         * <p>
         * 3. After persist check the hashcode since set has probelm with hash code
         * https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
         */
        @Column(insertable = false, updatable = false)
        long owner;

        public Role(String name) {
            this.name = name;
        }
    }

    @Repository
    interface EmployeeRepository extends JpaRepository<Employee, Long> {
    }
}
