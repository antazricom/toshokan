package com.antazri.data.impl;

import com.antazri.data.BookRepository;
import com.antazri.model.Author;
import com.antazri.model.Book;
import com.antazri.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Component @Repository de Book implémentant l'interface IBookDao, il permet l'accès et l'envoie des objets Book
 * dans la base de données via le @PersistenceContext de Hibernate
 */
@Repository
@Scope("prototype")
@Transactional
public class BookRepositoryImpl extends BasicRepositoryImpl<Book, Integer> implements BookRepository {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public BookRepositoryImpl() {
        super(Book.class);
    }

    @Override
    public Optional<Book> findById(Integer integer) {
        return super.findById(integer);
    }

    @Override
    public Optional<Book> findByUuid(String uuid) {
        return super.findByUuid(uuid);
    }

    @Override
    public Iterable<Book> findAll() {
        return super.findAll();
    }

    @Override
    public Book add(Book book) {
        return super.add(book);
    }

    @Override
    public Book update(Book book) {
        return super.update(book);
    }

    @Override
    public void delete(Book book) {
        super.delete(book);
    }

    @Override
    public Iterable<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public Iterable<Book> findByAuthor(Author author) {
        return null;
    }

    @Override
    public Iterable<Book> findByCategory(Category category) {
        return null;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return null;
    }

    @Override
    public Iterable<Book> findByPublicationYear(int year) {
        return null;
    }

    private enum Queries {
        ;

        private String query;

        Queries(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return this.query;
        }
    }
}
