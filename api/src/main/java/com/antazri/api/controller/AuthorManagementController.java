package com.antazri.api.controller;

import com.antazri.api.services.IAuthorService;
import com.antazri.exceptions.AuthorException;
import com.antazri.model.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorManagementController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final IAuthorService authorService;

    public AuthorManagementController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping(value = "/details/{uuid}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Author getByUuid(@PathVariable("uuid") String uuid) {
        Author author;

        try {
            author = authorService.findByUuid(uuid);
        } catch (Exception e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }

        return author;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Author> postAddAuthor(@RequestBody Author author) {
        Author addedAuthor;

        try {
            addedAuthor = authorService.add(author);
        } catch (AuthorException e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        return new ResponseEntity(addedAuthor, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Author> putUpdateAuthor(@RequestBody Author author) {
        Author updatedAuthor;

        try {
            updatedAuthor = authorService.update(author);
        } catch (AuthorException e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        return new ResponseEntity(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteAuthor(@PathVariable("uuid") String uuid) {
        try {
            authorService.delete(authorService.findByUuid(uuid));
        } catch (AuthorException e) {
            logger.trace(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
