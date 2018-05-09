package hu.unideb.inf.lev.carservice.dao.impl;

import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericDAOImpl<T, Id extends Serializable> implements hu.unideb.inf.lev.carservice.dao.GenericDAO<T, Id> {
    private Class<T> type;
    protected EntityManager entityManager = EntityManagerFactoryHelper.getEntityManager();

    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void persist(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public T findById(Id id) {
        return entityManager.find(type, id);
    }

    @Override
    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Id id) {
        this.delete(entityManager.getReference(type, id));
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("SELECT p FROM " + type.getSimpleName() + " p", type).getResultList();
    }
}
