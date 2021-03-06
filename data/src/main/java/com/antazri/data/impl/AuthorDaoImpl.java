package com.antazri.data.impl;

import com.antazri.data.AuthorDao;
import com.antazri.model.Author;
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
 * Component @Repository de Author implémentant l'interface IAuthorDao, il permet l'accès et l'envoie des objets Author
 * dans la base de données via le @PersistenceContext de Hibernate
 */
@Repository
@Scope("prototype")
@Transactional
public class AuthorDaoImpl implements AuthorDao {

    private static final Logger logger = LogManager.getLogger(AuthorDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Author> findById(Integer id) {
        final String req = "SELECT * FROM public.author author WHERE author.id = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Author.class), id));
    }

    @Override
    public Optional<Author> findByUuid(String uuid) {
        final String req = "SELECT * FROM public.author author WHERE author.uuid = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Author.class), uuid));
    }

    @Override
    public Collection<Author> findByName(String name) {
        final String req = "SELECT * FROM public.author author WHERE concat(author.firstname, author.lastname) ilike ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Author.class), "%" + name + "%");
    }

    @Override
    public Collection<Author> add(Collection<Author> authors) {
        Collection<Author> persistedAuthors = new ArrayList<>();

        for (Author author : authors) {
            final Author added = add(author);
            persistedAuthors.add(added);
        }

        return persistedAuthors;
    }

    @Override
    public Collection<Author> update(Collection<Author> authors) {
        List<Author> updatedAuthors = new ArrayList<>();

        for (Author author : authors) {
            final Author updated = update(author);
            updatedAuthors.add(updated);
        }

        return updatedAuthors;
    }

    @Override
    public void delete(Collection<Author> authors) {
        for (Author author : authors) {
            delete(author);
        }
    }

    @Override
    public Collection<Author> findAll() {
        final String req = "SELECT * FROM public.author";

        return jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Author.class));
    }

    @Override
    public Author add(Author author) {
        String uuid = UUID.randomUUID().toString();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String req = "INSERT INTO public.author (uuid, firstname, lastname) VALUES (?, ?, ?)";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, uuid);
            ps.setString(2, author.getFirstname());
            ps.setString(3, author.getLastname());
            return ps;
        }, keyHolder);

        author.setId(keyHolder.getKey().intValue());
        author.setUuid(uuid);

        return author;
    }

    @Override
    public Author update(Author author) {
        final String req = "UPDATE public.author SET firstname = ? AND lastname = ? WHERE id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, author.getFirstname());
            ps.setString(2, author.getLastname());
            ps.setInt(3, author.getId());
            return ps;
        });

        return author;
    }

    @Override
    public void delete(Author author) {
        final String req =
                "DELETE FROM public.author WHERE id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, author.getId());
            return ps;
        });
    }
}
