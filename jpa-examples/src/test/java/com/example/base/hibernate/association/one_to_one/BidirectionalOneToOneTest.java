package com.example.base.hibernate.association.one_to_one;

import com.example.base.BaseH2Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

        User saved = em.persistFlushFind(khang);
        assertNotNull(saved.info);
        assertNotNull(saved.info.user);
    }

    @Test
    void oneToOne() {
        User savedKien = em.persistFlushFind(User.builder().userName("Kien").build());
        ContactInfo info1 = ContactInfo.builder().address("downtown").user(savedKien).build();
        ContactInfo info2 = ContactInfo.builder().address("chinaTown").user(savedKien).build();
        em.persist(info1);
        em.persist(info2);

        assertThrows(PersistenceException.class,
                () -> em.getEntityManager().createQuery("SELECT ci FROM " +
                        "BidirectionalOneToOneTest$ContactInfo ci")
                        .getResultList());
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
    @Entity(name = "BidirectionalOneToOneTest$ContactInfo")
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
