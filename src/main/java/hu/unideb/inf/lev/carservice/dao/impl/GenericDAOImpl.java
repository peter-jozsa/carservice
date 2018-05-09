package hu.unideb.inf.lev.carservice.dao.impl;

import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericDAOImpl<T, Id extends Serializable> implements hu.unideb.inf.lev.carservice.dao.GenericDAO<T, Id> {
    private Class<T> type;

    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void create(T entity) {
        EntityManager entityManager = EntityManagerFactoryHelper.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        EntityManager entityManager = EntityManagerFactoryHelper.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public T findById(Id id) {
        return EntityManagerFactoryHelper.getEntityManager().find(type, id);
    }

    @Override
    public void delete(T entity) {
        EntityManager entityManager = EntityManagerFactoryHelper.getEntityManager();

        entityManager.getTransaction().begin();
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<T> getAll() {
        EntityManager entityManager = EntityManagerFactoryHelper.getEntityManager();

        return entityManager
                .createQuery("SELECT p FROM " + type.getSimpleName() + " p", type)
                .getResultList();
    }
}
