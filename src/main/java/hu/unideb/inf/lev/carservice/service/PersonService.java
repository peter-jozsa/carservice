package hu.unideb.inf.lev.carservice.service;

import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.util.List;

/**
 * This interface describes the look of the service which manages {@link Person} entities.
 */
public interface PersonService {
    /**
     * Gets a person entity by its unique identifier.
     * @param id Unique identifier of the person entity.
     * @return A person entity with same id as requested or <code>null</code> if it could not be found.
     */
    Person getPersonsById(Long id);

    /**
     * Creates a new person entity.
     * @param person A person entity
     * @throws ValidationException When the given person entity is not complete.
     */
    void createPerson(Person person) throws ValidationException;

    /**
     * Updates a person entity.
     * @param person A person entity
     * @throws ValidationException When the given entity is not valid.
     * @throws EntityNotFoundException When the given entity could not be loaded from the database.
     */
    void updatePerson(Person person) throws ValidationException, EntityNotFoundException;

    /**
     * Deletes a person entity by its unique identifier.
     * @param id Unique identifier
     * @throws EntityNotFoundException When the entity could not be loaded form the database.
     */
    void deletePersonById(Long id) throws EntityNotFoundException;

    /**
     * Validates a person entity.
     * @param person An entity to be validated.
     * @return <code>true</code> if validation was successful.
     * @throws ValidationException When the given entity is invalid.
     */
    boolean validatePerson(Person person) throws ValidationException;

    /**
     * Gets all person entities.
     * @return A list of person entities.
     */
    List<Person> getAllPerson();

    /**
     * Gets all person entities matching the provided string.
     * @param str A string value
     * @return A list of person entities matching the criteria.
     */
    List<Person> textSearchPerson(String str);

    /**
     * Increases the discount of the person.
     * @param person
     * @throws ValidationException When the provided person entity could not be updated since it is invalid.
     * @throws EntityNotFoundException When the given entity could not be loaded from the database.
     */
    void increaseDiscountOfPerson(Person person) throws ValidationException, EntityNotFoundException;
}
