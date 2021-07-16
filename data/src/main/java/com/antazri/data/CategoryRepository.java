package com.antazri.data;

import com.antazri.model.Category;

import java.util.Optional;

public interface CategoryRepository extends AbstractBasicRepository<Category, Integer> {

    @Override
    Optional<Category> findById(Integer integer);

    @Override
    Optional<Category> findByUuid(String uuid);

    @Override
    Iterable<Category> findAll();

    @Override
    Category add(Category category);

    @Override
    Category update(Category category);

    @Override
    void delete(Category category);
}
