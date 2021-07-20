package com.antazri.data;

import com.antazri.model.Author;

import java.util.Collection;

public interface AuthorDao extends AbstractBasicDao<Author, Integer> {

    Collection<Author> findByName(String name);
}
