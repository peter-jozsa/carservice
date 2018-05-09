package hu.unideb.inf.lev.carservice.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
    void persist(T entity);
    T findById(ID id);
    void delete(T entity);
    void delete(ID id);
    List<T> getAll();
}
