package com.antazri.data.impl;

import com.antazri.data.CategoryDao;
import com.antazri.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-data-test.xml"})
@Transactional
class CategoryDaoImplIntegrationTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    @DisplayName("FindAll should return all instances")
    void whenFindAll_shouldReturnAllCategoriesFromDb() {
        // When
        final List<Category> categories = new ArrayList<>(categoryDao.findAll());

        // Then
        assertEquals(9, categories.size());
    }

    @Test
    @DisplayName("FindById should return instance with same id")
    void whenFindById_shouldReturnSingleWithSameId() {
        // When
        final Optional<Category> categoryOpt = categoryDao.findById(1);

        // Then
        assertTrue(categoryOpt.isPresent());
    }
}