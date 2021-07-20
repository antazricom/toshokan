package com.antazri.data;

import com.antazri.model.Category;

import java.util.Optional;

public interface CategoryDao extends AbstractBasicDao<Category, Integer> {

    Optional<Category> findByName(String name);
}
