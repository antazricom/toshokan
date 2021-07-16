package com.antazri.data;

import com.antazri.model.Author;
import com.antazri.model.Book;
import com.antazri.model.Category;

import java.util.Optional;

public interface BookRepository extends AbstractBasicRepository<Book, Integer> {

    @Override
    Optional<Book> findById(Integer integer);

    @Override
    Optional<Book> findByUuid(String uuid);

    @Override
    Iterable<Book> findAll();

    @Override
    Book add(Book book);

    @Override
    Book update(Book book);

    @Override
    void delete(Book book);

    Iterable<Book> findByTitle(String title);
    Iterable<Book> findByAuthor(Author author);
    Iterable<Book> findByCategory(Category category);
    Optional<Book> findByIsbn(String isbn);
    Iterable<Book> findByPublicationYear(int year);
}
