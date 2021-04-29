package com.example.base.hibernate.association.one_to_one;

import com.example.base.BaseH2Test;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by khangld5 on Apr 23, 2021
 */
class BestOToOTest extends BaseH2Test {

    @Test
    void fetchContactInfo() {
        User savedKien = em.persistFlushFind(new User("Kien"));
        ContactInfo ci = new ContactInfo("china", savedKien);
        em.persistAndFlush(ci);
        ContactInfo saved = em.find(ContactInfo.class, savedKien.id);
        assertNotNull(saved);
    }

    @Entity
    @NoArgsConstructor
    private static class User {
        @Id
        @GeneratedValue
        private Long id;

        String userName;

        @Embedded
        @AttributeOverride(name = "state", column = @Column(name = "ADDR_STATE"))
        Address address;

        public User(String name) {
            this.userName = name;
        }
    }

    @Embeddable
    private static class Address {
        private String state;
    }

    @Entity(name = "BestOToOTest$ContactInfo")
    @NoArgsConstructor
    private static class ContactInfo {
        @Id
        private Long id;

        String address;

        @MapsId
        @OneToOne //default load Eager no matter insert
        @JoinColumn(name = "id")
        User user;

        public ContactInfo(String address, User user) {
            this.address = address;
            this.user = user;
        }
    }
}
