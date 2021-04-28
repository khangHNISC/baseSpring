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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class BidirectionalManyToManyTest extends BaseH2Test {

    private Author xMen;

    @BeforeEach
    void setup() {
        Book b1 = new Book("some book 1");
        Book b2 = new Book("some book 2");
        Author xMen = new Author("XMEN");
        b1.addAuthor(xMen);
        b2.addAuthor(xMen);
        xMen.addBooks(b1);
        xMen.addBooks(b2);
        this.xMen = em.persistFlushFind(xMen);
    }

    @Test
    void testAdd() {
        assertFalse(xMen.getBooks().isEmpty());
    }

    @Entity
    @NoArgsConstructor
    static class Author {
        @Id
        @GeneratedValue
        private Long id;

        String name;

        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinTable(name = "author_book_bidirection",
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

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Author)) {
                return false;
            }
            Author other = (Author) obj;
            return id != null && id.equals(other.id);
        }

        @Override
        public int hashCode() {
            return getClass().hashCode();
        }
    }

    @Entity(name = "BidirectionalManyToManyTest$Book")
    @NoArgsConstructor
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    static class Book {

        @Id
        @GeneratedValue
        private Long id;

        @NaturalId
        @EqualsAndHashCode.Include
        String name;

        @ManyToMany(mappedBy = "books")
        private final Set<Author> authors = new HashSet<>();

        public Book(String name) {
            this.name = name;
        }

        public Set<Author> getAuthors() {
            return Collections.unmodifiableSet(authors);
        }

        public void addAuthor(Author author) {
            authors.add(author);
        }
    }
}
