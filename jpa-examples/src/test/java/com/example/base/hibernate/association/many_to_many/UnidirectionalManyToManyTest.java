package com.example.base.hibernate.association.many_to_many;

import com.example.base.BaseH2Test;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UnidirectionalManyToManyTest extends BaseH2Test {

    private Author xMen;

    @BeforeEach
    void setup() {
        Book b1 = new Book("some book 1");
        Book b2 = new Book("some book 2");
        Author xMen = new Author("XMEN");
        xMen.addBooks(b1);
        xMen.addBooks(b2);
        this.xMen = em.persistFlushFind(xMen);
    }

    @Test
    void testAdd() {
        assertFalse(xMen.getBooks().isEmpty());
    }

    @Test
    void testRemoveParent() {
        em.remove(xMen);
        em.flush();

        List<Book> books = em.getEntityManager().createQuery("SELECT b FROM " +
                "UnidirectionalManyToManyTest$Book b", Book.class)
                .getResultList();

        assertFalse(books.isEmpty());
    }

    @Entity
    @NoArgsConstructor
    static class Author {
        @Id
        @GeneratedValue
        long id;

        String name;

        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinTable(name = "author_book_unidirection",
                joinColumns = @JoinColumn(name = "author_id"),
                inverseJoinColumns = @JoinColumn(name = "book_id")
        )
        private final Set<Book> books = new HashSet<>();

        public Author(String name) {
            this.name = name;
        }

        public Set<Book> getBooks() {
            return Collections.unmodifiableSet(books);
        }

        public void addBooks(Book book) {
            books.add(book);
        }
    }

    @Entity(name = "UnidirectionalManyToManyTest$Book")
    @NoArgsConstructor
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    static class Book {

        @Id
        @GeneratedValue
        @EqualsAndHashCode.Include
        long id;

        @NaturalId
        String name;

        public Book(String name) {
            this.name = name;
        }
    }
}
