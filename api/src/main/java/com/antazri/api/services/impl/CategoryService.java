package com.antazri.api.services.impl;

import com.antazri.api.services.ICategoryService;
import com.antazri.data.CategoryDao;
import com.antazri.exceptions.CategoryException;
import com.antazri.model.Category;
import com.antazri.model.utils.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService implements ICategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category findById(int id) {
        Optional<Category> categoryOptional = categoryDao.findById(id);

        if (categoryOptional.isEmpty()) {
            logger.error("No result found with ID: " + id);
        }

        return categoryOptional.get();
    }

    /**
     * Récupération d'une instance de {@link Category} en fonction de son attribut unique UUID via la couche Consumer.
     * Si aucune instance n'est trouvée, une exception est levée.
     *
     * @param uuid Un string définissant l'attribut UUID recherché
     * @return un objet {@link Category} dont l'attribut UUID correspond à celui passé en paramètre
     */
    @Override
    public Category findByUuid(String uuid) {
        Optional<Category> categoryOptional = categoryDao.findByUuid(uuid);

        if (categoryOptional.isEmpty()) {
            logger.error("No result found with UUID: " + uuid);
        }

        return categoryOptional.get();
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        categoryDao.findAll().forEach(category -> categories.add(category));
        return categories;
    }

    @Override
    public Category add(Category category) throws CategoryException {
        category.setUuid(UUID.randomUUID().toString());

        try {
            category = categoryDao.add(category);
        } catch (Exception e) {
            logger.error(Message.getAppMessages().getString("error.category") + category);
            throw new CategoryException(Message.getAppMessages().getString("error.category") + category);
        }

        return category;
    }

    @Override
    public Category update(Category category) throws CategoryException {
        try {
            category = categoryDao.update(category);
        } catch (Exception e) {
            logger.error(Message.getAppMessages().getString("error.category") + category);
            throw new CategoryException(Message.getAppMessages().getString("error.category") + category);
        }

        return category;
    }

    @Override
    public void delete(Category category) throws CategoryException {
        try {
            categoryDao.delete(category);
            categoryDao.findById(category.getId());
        } catch (Exception e) {
            logger.error(Message.getAppMessages().getString("error.category") + category);
            throw new CategoryException(Message.getAppMessages().getString("error.category") + category);
        }
    }
}
