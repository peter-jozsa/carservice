package hu.unideb.inf.lev.carservice.dao;

import hu.unideb.inf.lev.carservice.model.Car;

import java.util.List;

/**
 * This interface describes the Data Access Object of car entities.
 */
public interface CarDAO extends GenericDAO<Car, Long> {
    /**
     * Finds all car entities by registration number/VIN partially.
     * @param str The partial registration number or VIN you are looking for.
     * @return A list of car entities matching the criteria.
     */
    List<Car> findByString(String str);
}
