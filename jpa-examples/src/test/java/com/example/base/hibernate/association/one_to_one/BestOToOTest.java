package com.example.base.hibernate.association.one_to_one;

import com.example.base.BaseH2Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.*;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by khangld5 on Apr 23, 2021
 */
class BestOToOTest extends BaseH2Test {

    @Test
    void fetchContactInfo() {
        User savedKien = em.persistFlushFind(User.builder().userName("Kien").build());
        ContactInfo ci = ContactInfo.builder().address("china").user(savedKien).build();
        em.persistAndFlush(ci);
        ContactInfo saved = em.find(ContactInfo.class, savedKien.id);
        assertNotNull(saved);
    }

    @Builder
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        @Id
        @GeneratedValue
        long id;

        String userName;
    }

    @Builder
    @Entity(name = "BestOToOTest$ContactInfo")
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ContactInfo {
        @Id
        long id;

        String address;

        @MapsId
        @OneToOne //default load Eager no matter insert
        @JoinColumn(name = "id")
        User user;
    }
}
