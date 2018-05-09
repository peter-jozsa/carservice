package hu.unideb.inf.lev.carservice.dao.impl;

import hu.unideb.inf.lev.carservice.dao.PersonDAO;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;

import java.util.List;

public class PersonDAOImpl extends GenericDAOImpl<Person, Long> implements PersonDAO {
    @Override
    public List<Person> findAllByName(String name) {
        return EntityManagerFactoryHelper.getEntityManager()
                .createQuery("SELECT p FROM Person p WHERE lower(CONCAT(p.lastName, ' ', p.firstName)) LIKE lower('%" + name + "%')", Person.class)
                .getResultList();
    }
}
