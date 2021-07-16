package com.antazri.data;

import java.util.Optional;

public interface AbstractBasicRepository<T, ID> {

    Optional<T> findById(ID id);
    Optional<T> findByUuid(String uuid);
    Iterable<T> findAll();
    T add(T t);
    T update(T t);
    void delete(T t);
}
