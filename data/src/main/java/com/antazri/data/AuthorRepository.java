package com.antazri.data;

import com.antazri.model.Author;

import java.util.Optional;

public interface AuthorRepository extends AbstractBasicRepository<Author, Integer> {

    @Override
    Optional<Author> findById(Integer id);

    @Override
    Optional<Author> findByUuid(String uuid);

    @Override
    Iterable<Author> findAll();

    @Override
    Author add(Author author);

    @Override
    Author update(Author author);

    @Override
    void delete(Author author);
}
