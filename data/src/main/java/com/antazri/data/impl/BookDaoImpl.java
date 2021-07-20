package com.antazri.data.impl;

import com.antazri.data.BookDao;
import com.antazri.model.Author;
import com.antazri.model.Book;
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

/**
 * Component @Repository de Book implémentant l'interface IBookDao, il permet l'accès et l'envoie des objets Book
 * dans la base de données via le @PersistenceContext de Hibernate
 */
@Repository
@Scope("prototype")
@Transactional
public class BookDaoImpl implements BookDao {

    private final Logger logger = LogManager.getLogger(BookDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Book> findById(Integer id) {
        final String req = "SELECT * FROM public.book book WHERE book.id = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Book.class), id));
    }

    @Override
    public Optional<Book> findByUuid(String uuid) {
        final String req = "SELECT * FROM public.book book WHERE book.uuid = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Book.class), uuid));
    }

    @Override
    public Collection<Book> findByTitle(String title) {
        final String req = "SELECT * FROM public.book book WHERE book.title = ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Book.class), "%" + title + "%");
    }

    @Override
    public Collection<Book> findByAuthor(Author author) {
        final String req = "SELECT * FROM public.book book WHERE book.author_id = ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Book.class), author.getId());
    }

    @Override
    public Collection<Book> findByCategory(Category category) {
        final String req = "SELECT * FROM public.book book WHERE book.category_id = ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Book.class), category.getId());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        final String req = "SELECT * FROM public.book book WHERE book.isbn = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Book.class), isbn));
    }

    @Override
    public Collection<Book> findByPublicationYear(int year) {
        final String req = "SELECT * FROM public.book book WHERE book.publication_year = ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Book.class), year);
    }

    @Override
    public Collection<Book> add(Collection<Book> books) {
        List<Book> addedBooks = new ArrayList<>();

        for (Book book : books) {
            Book b = add(book);
            addedBooks.add(b);
        }

        return addedBooks;
    }

    @Override
    public Collection<Book> update(Collection<Book> books) {
        List<Book> updatedBooks = new ArrayList<>();

        for (Book book : books) {
            Book b = update(book);
            updatedBooks.add(b);
        }

        return updatedBooks;
    }

    @Override
    public void delete(Collection<Book> books) {
        for (Book book : books) {
            delete(book);
        }
    }

    @Override
    public Collection<Book> findAll() {
        final String req = "SELECT * FROM public.book";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book add(Book book) {
        String uuid = UUID.randomUUID().toString();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String req = "INSERT INTO public.book " +
                "(uuid, title, publication_year, isbn, author_id, category_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, uuid);
            ps.setString(2, book.getTitle());
            ps.setInt(3, book.getPublicationYear());
            ps.setString(4, book.getIsbn());
            ps.setInt(5, book.getAuthor().getId());
            ps.setInt(6, book.getCategory().getId());
            return ps;
        }, keyHolder);

        book.setId(keyHolder.getKey().intValue());
        book.setUuid(uuid);

        return book;
    }

    @Override
    public Book update(Book book) {
        final String req = "UPDATE public.book " +
                "SET title = ? " +
                "AND publication_year = ? " +
                "AND isbn = ? " +
                "AND author_id = ? " +
                "AND category_id = ? " +
                "WHERE id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPublicationYear());
            ps.setString(3, book.getIsbn());
            ps.setInt(4, book.getAuthor().getId());
            ps.setInt(5, book.getCategory().getId());
            ps.setInt(6, book.getId());
            return ps;
        });

        return book;
    }

    @Override
    public void delete(Book book) {
        final String req = "DELETE FROM public.book book WHERE book.id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, book.getId());
            return ps;
        });
    }
}
