package com.antazri.data.impl;

import com.antazri.data.CategoryRepository;
import com.antazri.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Scope("prototype")
@Transactional
public class CategoryRepositoryImpl extends BasicRepositoryImpl<Category, Integer> implements CategoryRepository {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public CategoryRepositoryImpl() {
        super(Category.class);
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return super.findById(integer);
    }

    @Override
    public Optional<Category> findByUuid(String uuid) {
        return super.findByUuid(uuid);
    }

    @Override
    public Iterable<Category> findAll() {
        return super.findAll();
    }

    @Override
    public Category add(Category category) {
        return super.add(category);
    }

    @Override
    public Category update(Category category) {
        return super.update(category);
    }

    @Override
    public void delete(Category category) {
        super.delete(category);
    }

    private enum Queries {
        ;

        private String query;

        Queries(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return this.query;
        }
    }
}
