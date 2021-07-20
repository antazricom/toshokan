package com.antazri.data.impl;

import com.antazri.data.ArtistDao;
import com.antazri.model.Artist;
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
public class ArtistDaoImpl implements ArtistDao {

    private static final Logger logger = LogManager.getLogger(ArtistDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public ArtistDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Artist> findById(Integer id) {
        final String req = "SELECT * FROM public.artist artist WHERE artist.id = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Artist.class), id));
    }

    @Override
    public Optional<Artist> findByUuid(String uuid) {
        final String req = "SELECT * FROM public.artist artist WHERE artist.uuid = ?";
        return Optional.ofNullable(this.jdbcTemplate.queryForObject(req, new BeanPropertyRowMapper<>(Artist.class), uuid));
    }

    @Override
    public Collection<Artist> findByName(String name) {
        final String req = "SELECT * FROM public.artist artist WHERE artist.name ilike ?";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Artist.class), "%" + name + "%");
    }

    @Override
    public Collection<Artist> findAll() {
        final String req = "SELECT * FROM public.artist";
        return this.jdbcTemplate.query(req, new BeanPropertyRowMapper<>(Artist.class));
    }

    @Override
    public Artist add(Artist artist) {
        String uuid = UUID.randomUUID().toString();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String req = "INSERT INTO public.artist (uuid, name) VALUES (?, ?)";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, uuid);
            ps.setString(2, artist.getName());
            return ps;
        }, keyHolder);

        artist.setId(keyHolder.getKey().intValue());
        artist.setUuid(uuid);

        return artist;
    }

    @Override
    public Collection<Artist> add(Collection<Artist> artists) {
        List<Artist> addedArtists = new ArrayList<>();

        for (Artist artist : artists) {
            Artist a = add(artist);
            addedArtists.add(a);
        }

        return addedArtists;
    }

    @Override
    public Artist update(Artist artist) {
        final String req = "UPDATE public.artist artist SET name = ? WHERE id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, artist.getName());
            ps.setInt(2, artist.getId());
            return ps;
        });

        return artist;
    }

    @Override
    public Collection<Artist> update(Collection<Artist> artists) {
        List<Artist> updatedArtists = new ArrayList<>();

        for (Artist artist : artists) {
            Artist a = update(artist);
            updatedArtists.add(a);
        }

        return updatedArtists;
    }

    @Override
    public void delete(Artist artist) {
        final String req = "DELETE FROM public.artist artist WHERE artist.id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, artist.getId());
            return ps;
        });

    }

    @Override
    public void delete(Collection<Artist> artists) {
        for (Artist artist : artists) {
            delete(artist);
        }
    }
}
