package com.antazri.api.controller;

import com.antazri.api.services.*;
import com.antazri.exceptions.BookException;
import com.antazri.model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookManagementController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final IBookService bookService;
    private final IAuthorService authorService;
    private final ICategoryService categoryService;

    public BookManagementController(IBookService bookService, IAuthorService authorService, ICategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return new ResponseEntity(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/details/{uuid}")
    public ResponseEntity<Book> getByUuid(@PathVariable("uuid") String uuid) {
        Book book;

        try {
            book = bookService.findByUuid(uuid);
        } catch (Exception e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }

        return new ResponseEntity(book, HttpStatus.OK);
    }

    @GetMapping("/title")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
        return new ResponseEntity(bookService.findByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/author/{uuid}")
    public ResponseEntity<List<Book>> findByAuthor(@PathVariable("uuid") String uuid) {
        List<Book> booksByAuthor;

        try {
            booksByAuthor = bookService.findByAuthor(authorService.findByUuid(uuid));
        } catch (Exception e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }

        return new ResponseEntity(booksByAuthor, HttpStatus.OK);
    }

    @GetMapping("/category/{uuid}")
    public ResponseEntity<List<Book>> findByCategory(@PathVariable("uuid") String uuid) {
        List<Book> booksByCategory;

        try {
            booksByCategory = bookService.findByCategory(categoryService.findByUuid(uuid));
        } catch (Exception e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }

        return new ResponseEntity(booksByCategory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> add(@RequestBody Book book) {
        Book addedBook;

        try {
            addedBook = bookService.add(book);
        } catch (BookException e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        return new ResponseEntity(addedBook, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Book> update(@RequestBody Book book) {
        Book updatedBook;

        try {
            updatedBook = bookService.update(book);
        } catch (BookException e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        return new ResponseEntity(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable("uuid") String uuid) {
        try {
            bookService.delete(bookService.findByUuid(uuid));
        } catch (BookException e) {
            logger.error(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
