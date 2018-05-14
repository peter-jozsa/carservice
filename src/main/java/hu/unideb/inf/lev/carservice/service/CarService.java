package hu.unideb.inf.lev.carservice.service;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.util.List;

/**
 * This interface describes the look of the service which manages {@link Car} entities.
 */
public interface CarService {
    /**
     * Gets a car entity by its unique identifier.
     * @param id Unique identifier of the car.
     * @return A car entity with same id as provided or <code>null</code> if it could not be found.
     */
    Car getCarById(Long id);

    /**
     * Creates a new car entity.
     * @param car A car entity
     * @throws ValidationException When the given car entity is not complete.
     */
    void createCar(Car car) throws ValidationException;

    /**
     * Updates a car entity.
     * @param car A car entity
     * @throws ValidationException When the given entity is not valid.
     * @throws EntityNotFoundException When the given entity could not be loaded from the database.
     */
    void updateCar(Car car) throws ValidationException, EntityNotFoundException;

    /**
     * Deletes a car entity by its unique identifier.
     * @param id Unique identifier
     * @throws EntityNotFoundException When the entity could not be loaded form the database.
     */
    void deleteCarById(Long id) throws EntityNotFoundException;

    /**
     * Validates a car entity.
     * @param car An entity to be validated.
     * @return <code>true</code> if validation was successful.
     * @throws ValidationException When the given entity is invalid.
     */
    boolean validateCar(Car car) throws ValidationException;

    /**
     * Gets all car entities.
     * @return A list of car entities
     */
    List<Car> getAllCar();

    /**
     * Gets all car entities matching the provided string.
     * @param str A string value
     * @return A list of car entities matching the criteria.
     */
    List<Car> textSearchCar(String str);
}
