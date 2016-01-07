package com.alextim.dao;

import java.util.Collection;

public interface IBasicDAO<T> {
    Collection<T> findAll();

    T getById(Long id);
    void delete(T object);

    void getByIdThenDelete(Long id);

    T getFirst();
}
