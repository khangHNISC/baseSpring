package com.example.base.hibernate.association.one_to_one;

import com.example.base.BaseH2Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BidirectionalOneToOneTest extends BaseH2Test {

    @Test
    void testRelationship() {
        User khang = User.builder().userName("Khang").build();
        ContactInfo info = ContactInfo.builder().address("downtown").build();
        khang.setInfo(info);

        User saved = em.persistFlushFind(khang);
    }

    @Test
    void joinColumnUniqueTrue() {
        User kien = User.builder().userName("Kien").build();
        ContactInfo info1 = ContactInfo.builder().address("downtown").build();
        kien.setInfo(info1);
        User savedKien = em.persistFlushFind(kien);

        assertThrows(PersistenceException.class,
                () -> em.persistAndFlush(ContactInfo.builder().address("chinaTown").user(savedKien).build())
        );
    }

    @Test
    void optionalFalseOneToOne() {
        //non null relationship is required
        assertThrows(PersistenceException.class, () ->
                em.persistFlushFind(User.builder().userName("Kien").build()));
    }

    @Builder
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class User {
        @Id
        @GeneratedValue
        long id;

        String userName;

        @ToString.Exclude
        //mappedBy -> Parent class, this fetch Eager even those specify LAZY
        @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
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
