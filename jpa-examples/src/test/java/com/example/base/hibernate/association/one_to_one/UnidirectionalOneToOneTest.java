package com.example.base.hibernate.association.one_to_one;

import com.example.base.BaseH2Test;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by khangld5 on Apr 22, 2021
 */
public class UnidirectionalOneToOneTest extends BaseH2Test {

    @Test
    void testRelationship() {
        //em as first-level cache, data have not yet persisted to DB after persist(delay writing as late as possible)
        //flush: data persisted but still can be roll back if transaction had not committed
        User savedKien = em.persistFlushFind(new User("Kien"));
        ContactInfo info = new ContactInfo("downtown", savedKien);
        em.persist(info);

        //before create query, em auto flush all data to db
        ContactInfo saveInfo = em.getEntityManager().createQuery("SELECT ci FROM " +
                "UnidirectionalOneToOneTest$ContactInfo ci WHERE ci.user = :user", ContactInfo.class)
                .setParameter("user", savedKien)
                .getSingleResult();
        assertNotNull(saveInfo.user);
    }

    @Test
    void oneToOne() {
        User savedKien = em.persistFlushFind(new User("Kien"));
        ContactInfo info1 = new ContactInfo("downtown", savedKien);
        ContactInfo info2 = new ContactInfo("china", savedKien);
        em.persistAndFlush(info1);
        assertThrows(PersistenceException.class, () -> em.persistAndFlush(info2));
    }

    @Entity
    @NoArgsConstructor
    private static class User {
        @Id
        @GeneratedValue
        //if id was passed then detach Entity Exception will throw
        private Long id;

        String userName;

        public User(String name) {
            this.userName = name;
        }
    }

    @Entity(name = "UnidirectionalOneToOneTest$ContactInfo") //require name for createQuery hsql
    @NoArgsConstructor
    private static class ContactInfo {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @SequenceGenerator(
                name = "contact_info_id_sq",
                allocationSize = 1
        )
        private Long id;

        String address;

        @OneToOne
        @JoinColumn(name = "user_id", unique = true)
        User user; //create a foreign key linking to User @Id, name doesn't matter (generally associate class + _id)

        public ContactInfo(String address, User user) {
            this.address = address;
            this.user = user;
        }
    }
}