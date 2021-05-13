package com.example.base.hibernate.association.one_to_one;

import com.example.base.BaseJpaH2Test;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BidirectionalOneToOneTest extends BaseJpaH2Test {

    @Test
    void joinColumnUniqueTrue() {
        ContactInfo info1 = new ContactInfo("downtown");
        User kien = new User("Kien");
        kien.setInfo(info1);
        User savedKien = em.persistFlushFind(kien);

        ContactInfo info2 = new ContactInfo("downtown");
        info2.user = savedKien;
        assertThrows(PersistenceException.class, () -> em.persistAndFlush(info2));
    }

  /*  @Test
    void optionalFalseOneToOneMandatoryChild() {
        //non null relationship is required
        User kien = new User("Kien");
        assertThrows(PersistenceException.class, () -> em.persistFlushFind(kien));
    }*/

    @Test
    void optionalFalseOneMandatoryParent() {
        ContactInfo info1 = new ContactInfo("downtown");
        assertThrows(PersistenceException.class, () -> em.persistAndFlush(info1));
    }

    @Entity
    @NoArgsConstructor
    private static class User {
        @Id
        @GeneratedValue
        private Long id;

        String userName;

        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
        //mappedBy marked which one is Parent class, this fetch Eager even those specify LAZY
        ContactInfo info;

        public User(String name) {
            this.userName = name;
        }

        public void setInfo(ContactInfo info) {
            if (info != null) {
                info.user = this;
            }
            this.info = info;
        }
    }

    @Entity
    @NoArgsConstructor
    private static class ContactInfo {
        @Id
        @GeneratedValue
        private Long id;

        String address;

        @OneToOne(optional = false)
        @JoinColumn(name = "user_id", unique = true)
        //this will specify foreign key
        private User user;

        public ContactInfo(String address) {
            this.address = address;
        }
    }
}
