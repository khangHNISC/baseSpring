package com.example.base.hibernate.association.one_to_one;

import com.example.base.BaseH2Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by khangld5 on Apr 22, 2021
 */
class UnidirectionalOneToOneTest extends BaseH2Test {

    @Test
    void testRelationship() {
        User kien = User.builder().userName("Kien").build();
        em.persist(kien);

        //em as first-level cache, data have not yet persisted (delay writing as late as possible)
        User savedKien = em.find(User.class, 1L);
        ContactInfo info = ContactInfo.builder().address("downtown").user(savedKien).build();
        em.persist(info);

        //before create query, em auto flush all data to db
        ContactInfo saveInfo = em.createQuery("select ci from ContactInfo1 ci where ci.user = :user", ContactInfo.class)
                .setParameter("user", kien)
                .getSingleResult();
        assertNotNull(saveInfo.user);
    }

    @Test
    void oneToOne() {
        User kien = User.builder().userName("Kien").build();
        em.persist(kien);

        User savedKien = em.find(User.class, 1L);
        ContactInfo info1 = ContactInfo.builder().address("downtown").user(savedKien).build();
        ContactInfo info2 = ContactInfo.builder().address("chinaTown").user(savedKien).build();
        em.persist(info1);
        em.persist(info2);

        assertThrows(Exception.class, () -> {
        });
    }

    @Builder
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        //if id was passed then detach Entity Exception will throw
        long id;

        String userName;
    }

    @Builder
    @Entity(name = "ContactInfo1") //require name for createQuery hsql
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ContactInfo {
        @Id
        @GeneratedValue
        long id; //some how this goes to 2

        String address;

        @OneToOne
        @JoinColumn(name = "user_id", unique = true)
        User user; //create a foreign key linking to User @Id, name doesn't matter (generally associate class + _id)
    }
}
/**
 * 1. only create test entity
 * 2. why one - one not forcing
 */