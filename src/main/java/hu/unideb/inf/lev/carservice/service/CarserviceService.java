package hu.unideb.inf.lev.carservice.service;

import hu.unideb.inf.lev.carservice.model.Person;

import java.util.List;

public interface CarserviceService {
    Person getPersonsById(Long id);
    void createPerson(Person person);
    void updatePerson(Person person);
    List<Person> getAllPerson();
    List<Person> findPersonsByName();
}
