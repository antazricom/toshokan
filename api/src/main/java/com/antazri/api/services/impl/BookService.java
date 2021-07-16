package com.antazri.api.services.impl;

import com.antazri.api.services.IBookService;
import com.antazri.data.BookRepository;
import com.antazri.exceptions.BookException;
import com.antazri.model.*;
import com.antazri.model.utils.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implémentation de IBookBo permettant la gestion des transactions vers la couche DAO.
 */
@Service
public class BookService implements IBookService {

    private static final Logger logger = LogManager.getLogger(BookService.class);

    private final BookRepository bookDao;

    public BookService(BookRepository bookDao) {
        this.bookDao = bookDao;
    }

    /**
     * La méthode findById permet de retourner un objet Book selon son identifiant unique via le bean bookDao
     * injecté par Spring
     *
     * @param id est un int définissant l'attribut unique "id" de l'objet dans la base de données
     * @return un objet Book
     */
    @Override
    public Book findById(int id) {
        Optional<Book> bookOptional = bookDao.findById(id);

        if (bookOptional.isEmpty()) {
            logger.error("No result found with ID: " + id);
        }

        return bookOptional.get();
    }

    /**
     * Récupération d'une instance de {@link Book} en fonction de son attribut unique UUID via la couche Consumer.
     * Si aucune instance n'est trouvée, une exception est levée.
     *
     * @param uuid Un string définissant l'attribut UUID recherché
     * @return un objet {@link Book} dont l'attribut UUID correspond à celui passé en paramètre
     */
    @Override
    public Book findByUuid(String uuid) {
        Optional<Book> bookOptional = bookDao.findByUuid(uuid);

        if (bookOptional.isEmpty()) {
            logger.error("No result found with UUID: " + uuid);
        }

        return bookOptional.get();
    }

    /**
     * La méthode findByTitle permet de retourner une ou plusieurs occurences de Book depuis la base de données.
     * La requête SQL va comparer le paramètre à l'attribut "title" mis en minuscule via le bean bookDao
     * injecté par Spring
     *
     * @param title est un String permettant de retrouver un ou plusieurs Book
     * @return une List contenant un ou plusieurs objets Book
     */
    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();
        bookDao.findByTitle(title).forEach(book -> books.add(book));
        return books;
    }

    /**
     * La méthode findByAuthor permet de retourner une ou plusieurs occurences de Book depuis la base de
     * données selon le Author lié grâce à son identifiant unique via le bean bookDao injecté par Spring
     *
     * @param author est un objet Author
     * @return une List d'un ou plusieurs objets Book
     */
    @Override
    public List<Book> findByAuthor(Author author) {
        List<Book> books = new ArrayList<>();
        bookDao.findByAuthor(author).forEach(book -> books.add(book));
        return books;
    }

    /**
     * Retourne les occurrences de Book selon "category" via la couche DAO dont le bean est injecté par Spring
     *
     * @param category est un objet Category correspondant à l'attribut "category"
     * @return une Collection de Book
     */
    @Override
    public List<Book> findByCategory(Category category) {
        List<Book> books = new ArrayList<>();
        bookDao.findByCategory(category).forEach(book -> books.add(book));
        return books;
    }

    /**
     * Retourne l'occurence de {@link Book} dont l'attribut "isbn" via la couche DAO dont le bean est injecté par Spring
     *
     * @param isbn est un String définissant l'ISBN recherché
     * @return une instance de {@link Book} correspondante
     */
    @Override
    public Book findByIsbn(String isbn) {
        Optional<Book> bookOptional = bookDao.findByIsbn(isbn);

        if (bookOptional.isEmpty()) {
            logger.error("No result found with ISBN: " + isbn);
        }

        return bookOptional.get();
    }

    /**
     * La méthode findAll permet de retourner toutes les occurences de Book enregistrées dans la base de données via
     * le bean bookDao injecté par Spring
     *
     * @return une List d'objets Book
     */
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        bookDao.findAll().forEach(book -> books.add(book));
        return books;
    }

    /**
     * La méthode add permet d'enregistrer une instance de Book dans la base de données via le bean
     * bookDao injecté par Spring
     *
     * @param book est un objet Book envoyé et validé par Hibernate Validator
     * @return un objet Author correspondant à l'occurence enregistrée
     */
    @Override
    public Book add(Book book) throws BookException {
        book.setUuid(UUID.randomUUID().toString());

        try {
            book = bookDao.add(book);
        } catch (Exception e) {
            logger.error(Message.getAppMessages().getString("error.book") + book);
            throw new BookException(Message.getAppMessages().getString("error.book") + book);
        }

        return book;
    }

    /**
     * La méthode update permet de mettre à jour une instance de Book dans la base de données via le bean
     * bookDao injecté par Spring
     *
     * @param book est un objet Book envoyé et validé par Hibernate Validator
     * @return un objet Author correspondant à l'occurence modifiée
     */
    @Override
    public Book update(Book book) throws BookException {

        try {
            book = bookDao.update(book);
        } catch (Exception e) {
            logger.error(Message.getAppMessages().getString("error.book") + book);
            throw new BookException(Message.getAppMessages().getString("error.book") + book);
        }

        return book;
    }

    /**
     * La méthode add permet de supprimer une instance de Book dans la base de données via le bean bookDao
     * injecté par Spring
     *
     * @param book est un objet Book envoyé et validé par Hibernate Validator
     */
    @Override
    public void delete(Book book) throws BookException {
        try {
            bookDao.delete(book);
            bookDao.findById(book.getId());
        } catch (Exception e) {
            logger.error(Message.getAppMessages().getString("error.book") + book);
            throw new BookException(Message.getAppMessages().getString("error.book") + book);
        }
    }
}
