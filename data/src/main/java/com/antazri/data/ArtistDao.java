package com.antazri.data;

import com.antazri.model.Artist;

import java.util.Collection;

public interface ArtistDao extends AbstractBasicDao<Artist, Integer> {

    Collection<Artist> findByName(String name);
}
