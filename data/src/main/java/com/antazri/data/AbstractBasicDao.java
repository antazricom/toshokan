package com.antazri.data;

import java.util.Collection;
import java.util.Optional;

public interface AbstractBasicDao<T, ID> {

    Optional<T> findById(ID id);

    Optional<T> findByUuid(String uuid);

    Collection<T> findAll();

    T add(T t);

    Collection<T> add(Collection<T> t);

    T update(T t);

    Collection<T> update(Collection<T> t);

    void delete(T t);

    void delete(Collection<T> t);
}
