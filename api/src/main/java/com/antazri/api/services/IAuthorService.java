package com.antazri.api.services;

import com.antazri.exceptions.AuthorException;
import com.antazri.model.Author;

import java.util.List;

public interface IAuthorService {

    Author findById(int id);

    Author findByUuid(String uuid);

    List<Author> findAll();

    Author add(Author author) throws AuthorException;

    Author update(Author author) throws AuthorException;

    void delete(Author author) throws AuthorException;
}
