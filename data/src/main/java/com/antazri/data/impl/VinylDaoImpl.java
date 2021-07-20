package com.antazri.data.impl;

import com.antazri.data.VinylDao;
import com.antazri.model.Artist;
import com.antazri.model.Category;
import com.antazri.model.Vinyl;
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
public class VinylDaoImpl implements VinylDao {

    private static final Logger logger = LogManager.getLogger(VinylDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public VinylDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Vinyl> findById(Integer id) {
        final String req = "SELECT * FROM public.vinyl vinyl WHERE vinyl.id = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Vinyl.class), id));
    }

    @Override
    public Optional<Vinyl> findByUuid(String uuid) {
        final String req = "SELECT * FROM public.vinyl vinyl WHERE vinyl.uuid = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Vinyl.class), uuid));
    }

    @Override
    public Collection<Vinyl> findByTitle(String title) {
        final String req = "SELECT * FROM public.vinyl vinyl WHERE vinyl.title = ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Vinyl.class), "%" + title + "%");
    }

    @Override
    public Collection<Vinyl> findByArtist(Artist artist) {
        final String req = "SELECT * FROM public.vinyl vinyl WHERE vinyl.artist_id = ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Vinyl.class), artist.getId());
    }

    @Override
    public Collection<Vinyl> findByPublicationYear(int year) {
        final String req = "SELECT * FROM public.vinyl vinyl WHERE vinyl.publication_year = ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Vinyl.class), year);
    }

    @Override
    public Collection<Vinyl> findByCategory(Category category) {
        final String req = "SELECT * FROM public.vinyl vinyl WHERE vinyl.category_id = ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Vinyl.class), category.getId());
    }

    @Override
    public Collection<Vinyl> findAll() {
        final String req = "SELECT * FROM public.vinyl";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Vinyl.class));
    }

    @Override
    public Vinyl add(Vinyl vinyl) {
        String uuid = UUID.randomUUID().toString();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String req = "INSERT INTO public.vinyl " +
                "(uuid, title, publication_year, artist_id, category_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, uuid);
            ps.setString(2, vinyl.getTitle());
            ps.setInt(3, vinyl.getPublicationYear());
            ps.setInt(4, vinyl.getArtist().getId());
            ps.setInt(6, vinyl.getCategory().getId());
            return ps;
        }, keyHolder);

        vinyl.setId(keyHolder.getKey().intValue());
        vinyl.setUuid(uuid);

        return vinyl;
    }

    @Override
    public Collection<Vinyl> add(Collection<Vinyl> vinyls) {
        List<Vinyl> addedVinyls = new ArrayList<>();

        for (Vinyl vinyl : vinyls) {
            Vinyl v = add(vinyl);
            addedVinyls.add(v);
        }

        return addedVinyls;
    }

    @Override
    public Vinyl update(Vinyl vinyl) {
        final String req = "UPDATE public.vinyl " +
                "SET title = ? " +
                "AND publication_year = ? " +
                "AND artist_id = ? " +
                "AND category_id = ? " +
                "WHERE id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, vinyl.getTitle());
            ps.setInt(2, vinyl.getPublicationYear());
            ps.setInt(3, vinyl.getArtist().getId());
            ps.setInt(4, vinyl.getCategory().getId());
            ps.setInt(5, vinyl.getId());
            return ps;
        });

        return vinyl;
    }

    @Override
    public Collection<Vinyl> update(Collection<Vinyl> vinyls) {
        List<Vinyl> updatedVinyls = new ArrayList<>();

        for (Vinyl vinyl : vinyls) {
            Vinyl v = update(vinyl);
            updatedVinyls.add(v);
        }

        return updatedVinyls;
    }

    @Override
    public void delete(Vinyl vinyl) {
        final String req = "DELETE FROM public.vinyl vinyl WHERE vinyl.id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, vinyl.getId());
            return ps;
        });
    }

    @Override
    public void delete(Collection<Vinyl> vinyls) {
        for (Vinyl vinyl : vinyls) {
            delete(vinyl);
        }
    }
}
