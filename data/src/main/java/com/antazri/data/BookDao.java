package com.antazri.data;

import com.antazri.model.Author;
import com.antazri.model.Book;
import com.antazri.model.Category;

import java.util.Collection;
import java.util.Optional;

public interface BookDao extends AbstractBasicDao<Book, Integer> {

    Collection<Book> findByTitle(String title);

    Collection<Book> findByAuthor(Author author);

    Collection<Book> findByCategory(Category category);

    Optional<Book> findByIsbn(String isbn);

    Collection<Book> findByPublicationYear(int year);
}
