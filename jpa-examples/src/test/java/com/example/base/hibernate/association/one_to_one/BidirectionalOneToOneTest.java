package com.example.base.hibernate.association.one_to_one;

import com.example.base.BaseH2Test;
import lombok.*;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BidirectionalOneToOneTest extends BaseH2Test {

    @Test
    void testRelationship() {
        User khang = User.builder().userName("Khang").build();
        ContactInfo info = ContactInfo.builder().address("downtown").build();
        khang.setInfo(info);
        em.persist(khang);

        User saved = em.find(User.class, 1L);
        assertNotNull(saved.info);
        assertNotNull(saved.info.user);
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

        assertThrows(Exception.class, () -> {});
    }

    @Builder
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        long id;

        String userName;

        @ToString.Exclude
        @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        ContactInfo info;

        public void setInfo(ContactInfo info) {
            if (info != null) {
                info.user = this;
            }
            this.info = info;
        }
    }

    @Builder
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ContactInfo {
        @Id
        @GeneratedValue
        long id;

        String address;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", unique = true)
        User user;
    }
}
