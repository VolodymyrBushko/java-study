package com.example.springhibernate001.service;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T entity);

    T update(ID id, T entity);

    void delete(ID id);
}
