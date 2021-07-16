package com.antazri.data.impl;

import com.antazri.data.AuthorRepository;
import com.antazri.model.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Component @Repository de Author implémentant l'interface IAuthorDao, il permet l'accès et l'envoie des objets Author
 * dans la base de données via le @PersistenceContext de Hibernate
 */
@Repository
@Scope("prototype")
@Transactional
public class AuthorRepositoryImpl extends BasicRepositoryImpl<Author, Integer> implements AuthorRepository {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public AuthorRepositoryImpl() {
        super(Author.class);
    }

    @Override
    public Optional<Author> findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public Optional<Author> findByUuid(String uuid) {
        return super.findByUuid(uuid);
    }

    @Override
    public Iterable<Author> findAll() {
        return super.findAll();
    }

    @Override
    public Author add(Author author) {
        return super.add(author);
    }

    @Override
    public Author update(Author author) {
        return super.update(author);
    }

    @Override
    public void delete(Author author) {
        super.delete(author);
    }

    private enum Queries {
        ;

        private String query;

        Queries(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return this.query;
        }
    }
}
