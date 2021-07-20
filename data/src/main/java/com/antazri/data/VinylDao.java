package com.antazri.data;

import com.antazri.model.Artist;
import com.antazri.model.Category;
import com.antazri.model.Vinyl;

import java.util.Collection;

public interface VinylDao extends AbstractBasicDao<Vinyl, Integer> {

    Collection<Vinyl> findByTitle(String title);

    Collection<Vinyl> findByArtist(Artist artist);

    Collection<Vinyl> findByPublicationYear(int year);

    Collection<Vinyl> findByCategory(Category category);
}
