package com.antazri.api.controller;

import com.antazri.api.services.ICategoryService;
import com.antazri.exceptions.CategoryException;
import com.antazri.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryManagementController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final ICategoryService categoryService;

    public CategoryManagementController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/details/{uuid}")
    public ResponseEntity<Category> findByUuid(@PathVariable("uuid") String uuid) {
        Category category;

        try {
            category = categoryService.findByUuid(uuid);
        } catch (Exception e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> add(@RequestBody Category category) {
        Category addedCategory;

        try {
            addedCategory = categoryService.add(category);
        } catch (CategoryException e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category) {
        Category updatedCategory;

        try {
            updatedCategory = categoryService.update(category);
        } catch (CategoryException e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable("uuid") String uuid) {

        try {
            categoryService.delete(categoryService.findByUuid(uuid));
        } catch (CategoryException e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
