package com.antazri.data.impl;

import com.antazri.data.AbstractBasicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public abstract class BasicRepositoryImpl<T, ID> implements AbstractBasicRepository<T, ID> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final Class<T> type;

    public BasicRepositoryImpl(Class type) {
        this.type = type;
    }

    @Override
    public Optional<T> findById(ID id) {
        return null;
    }

    @Override
    public Optional<T> findByUuid(String uuid) {
        return null;
    }

    @Override
    public Iterable<T> findAll() {
        return null;
    }

    @Override
    public T add(T t) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public void delete(T t) {
    }

    private Class<T> getType() {
        return this.type;
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
