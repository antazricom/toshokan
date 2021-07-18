package com.antazri.data;

import java.util.List;
import java.util.Optional;

public interface AbstractBasicRepository<T, ID> {

    Optional<T> find(ID id);
    Optional<T> find(String uuid);
    Iterable<T> findAll();
    T add(T t);
    List<T> add(List<T> t);
    T update(T t);
    List<T> update(List<T> t);
    void delete(T t);
    void delete(List<T> t);
}
