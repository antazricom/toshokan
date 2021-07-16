package com.antazri.api.services;

import com.antazri.exceptions.CategoryException;
import com.antazri.model.Category;

import java.util.List;

public interface ICategoryService {

    Category findById(int id);

    Category findByUuid(String uuid);

    List<Category> findAll();

    Category add(Category category) throws CategoryException;

    Category update(Category category) throws CategoryException;

    void delete(Category category) throws CategoryException;
}
