package com.antazri.data;

import com.antazri.model.Author;

public interface AuthorRepository extends AbstractBasicRepository<Author, Integer> {

    @Override
    Iterable<Author> findAll();

    @Override
    Author add(Author author);

    @Override
    Author update(Author author);

    @Override
    void delete(Author author);
}
