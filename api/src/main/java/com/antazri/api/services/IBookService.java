package com.antazri.api.services;

import com.antazri.exceptions.BookException;
import com.antazri.model.*;

import java.util.List;

public interface IBookService {

    Book findById(int pId);

    Book findByUuid(String uuid);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(Author author);

    List<Book> findByCategory(Category category);

    Book findByIsbn(String isbn);

    List<Book> findAll();

    Book add(Book book) throws BookException;

    Book update(Book book) throws BookException;

    void delete(Book book) throws BookException;
}
