package com.antazri.api.services.impl;

import com.antazri.api.services.IAuthorService;
import com.antazri.data.AuthorDao;
import com.antazri.exceptions.AuthorException;
import com.antazri.model.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implémentation de IAuthorBo permettant la gestion des transactions vers la couche DAO.
 */
@Service
public class AuthorService implements IAuthorService {

    private static final Logger logger = LogManager.getLogger(AuthorService.class);

    private final AuthorDao authorDao;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    /**
     * La méthode findById permet de retourner un objet Author selon son identifiant unique via le bean authorDao injecté par Spring
     *
     * @param id est un int définissant l'attribut unique "id" de l'objet dans la base de données
     * @return un objet Author
     */
    @Override
    public Author findById(int id) {
        Optional<Author> authorOptional = authorDao.findById(id);

        if (authorOptional.isEmpty()) {
            logger.error("Author not found with ID: " + id);
        }

        return authorOptional.get();
    }

    /**
     * Récupération d'une instance de {@link Author} en fonction de son attribut unique UUID via la couche Consumer.
     * Si aucune instance n'est trouvée, une exception est levée.
     *
     * @param uuid Un string définissant l'attribut UUID recherché
     * @return un objet {@link Author} dont l'attribut UUID correspond à celui passé en paramètre
     */
    @Override
    public Author findByUuid(String uuid) {
        Optional<Author> authorOptional = authorDao.findByUuid(uuid);

        if (authorOptional.isEmpty()) {
            logger.error("Author not found with UUID: " + " '" + uuid + "'");
        }

        return authorOptional.get();
    }

    /**
     * La méthode findAll permet de retourner l'ensemble des occurences Author de la base de données via le bean authorDao injecté par Spring
     *
     * @return une List d'objets Author
     */
    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        authorDao.findAll().forEach(author -> authors.add(author));
        return authors;
    }

    /**
     * La méthode add permet d'enregistrer une instance de Author dans la base de données via le bean authorDao injecté par Spring
     *
     * @param author est un objet Author envoyé et validé par Hibernate Validator
     * @return un objet Author correspondant à l'occurence enregistrée
     */
    @Override
    public Author add(Author author) throws AuthorException {
        author.setUuid(UUID.randomUUID().toString());

        try {
            author = authorDao.add(author);
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getCause());
            throw new AuthorException("Add author error with: " + author);
        }

        return author;
    }

    /**
     * La méthode update permet de mettre à jour une instance de Author dans la base de données via le bean authorDao injecté par Spring
     *
     * @param author est un objet Author envoyé et validé par Hibernate Validator
     * @return un objet Author correspondant à l'occurence modifiée
     */
    @Override
    public Author update(Author author) throws AuthorException {
        try {
            author = authorDao.update(author);
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getCause());
            throw new AuthorException("Update author error with: " + author);
        }

        return author;
    }

    /**
     * La méthode add permet de supprimer une instance de Author de la base de données via le bean authorDao injecté par Spring
     *
     * @param author est un objet Author envoyé et validé par Hibernate Validator
     */
    @Override
    public void delete(Author author) throws AuthorException {
        try {
            authorDao.delete(author);
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getCause());
            logger.info("Verify relationship with Books");
            throw new AuthorException("Delete author error with: " + author);
        }
    }
}
