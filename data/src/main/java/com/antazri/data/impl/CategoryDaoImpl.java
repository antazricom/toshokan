package com.antazri.data.impl;

import com.antazri.data.CategoryDao;
import com.antazri.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
@Scope("prototype")
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    private final Logger logger = LogManager.getLogger(CategoryDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Category> findById(Integer id) {
        final String req = "SELECT * FROM public.category category WHERE category.id = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Category.class), id));
    }

    @Override
    public Optional<Category> findByUuid(String uuid) {
        final String req = "SELECT * FROM public.category category WHERE category.uuid = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Category.class), uuid));
    }

    @Override
    public Optional<Category> findByName(String name) {
        final String req = "SELECT * FROM public.category category WHERE category.name ilike ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Category.class), "%" + name + "%"));
    }

    @Override
    public Collection<Category> add(Collection<Category> categories) {
        List<Category> addedCategories = new ArrayList<>();

        for (Category category : categories) {
            Category c = add(category);
            addedCategories.add(c);
        }

        return addedCategories;
    }

    @Override
    public Collection<Category> update(Collection<Category> categories) {
        List<Category> updatedCategories = new ArrayList<>();

        for (Category category : categories) {
            Category c = update(category);
            updatedCategories.add(c);
        }

        return updatedCategories;
    }

    @Override
    public void delete(Collection<Category> categories) {
        for (Category category : categories) {
            delete(category);
        }
    }

    @Override
    public Collection<Category> findAll() {
        final String req = "SELECT * FROM public.category";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper(Category.class));
    }

    @Override
    public Category add(Category category) {
        String uuid = UUID.randomUUID().toString();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String req =
                "INSERT INTO public.category (uuid,name,type) VALUES (?,?,?)";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, uuid);
            ps.setString(2, category.getName());
            ps.setString(3, category.getType());
            return ps;
        }, keyHolder);

        category.setId(keyHolder.getKey().intValue());
        category.setUuid(uuid);

        return category;
    }

    @Override
    public Category update(Category category) {
        final String req =
                "UPDATE public.category " +
                        "SET name = ?" +
                        "AND type = ?" +
                        "WHERE id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, category.getName());
            ps.setString(2, category.getType());
            ps.setInt(3, category.getId());
            return ps;
        });

        return category;
    }

    @Override
    public void delete(Category category) {
        final String req =
                "DELETE FROM public.category WHERE id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, category.getId());
            return ps;
        });
    }
}
