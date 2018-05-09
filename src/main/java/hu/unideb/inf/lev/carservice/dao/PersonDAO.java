package hu.unideb.inf.lev.carservice.dao;

import hu.unideb.inf.lev.carservice.model.Person;

import java.util.List;

public interface PersonDAO extends GenericDAO<Person, Long> {
    List<Person> findAllByName(String name);
}
