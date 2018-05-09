package hu.unideb.inf.lev.carservice.dao.impl;

import hu.unideb.inf.lev.carservice.dao.PersonDAO;
import hu.unideb.inf.lev.carservice.model.Person;

import java.util.List;

public class PersonDAOImpl extends GenericDAOImpl<Person, Long> implements PersonDAO {
    @Override
    public List<Person> findAllByName(String name) {
        return entityManager
                .createQuery("SELECT p FROM Person p WHERE CONCAT(p.lastName, ' ', p.firstName) LIKE '%" + name + "%'", Person.class)
                .getResultList();
    }
}
