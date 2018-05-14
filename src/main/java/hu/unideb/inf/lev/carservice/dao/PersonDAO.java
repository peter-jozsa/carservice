package hu.unideb.inf.lev.carservice.dao;

import hu.unideb.inf.lev.carservice.model.Person;

import java.util.List;

/**
 * This interface describes the Data Access Object of person entities.
 */
public interface PersonDAO extends GenericDAO<Person, Long> {
    /**
     * Finds all person entities by name partially.
     * @param name The name you are looking for.
     * @return A list of person entities matching the given name.
     */
    List<Person> findAllByName(String name);
}
