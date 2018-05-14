package hu.unideb.inf.lev.carservice.dao;

import hu.unideb.inf.lev.carservice.model.JobType;

import java.util.List;

/**
 * This interface describes the Data Access Object of job type entities.
 */
public interface JobTypeDAO extends GenericDAO<JobType, Long> {
    /**
     * Finds all entities by name partially.
     * @param name The name you are looking for.
     * @return A list of entities with matching names.
     */
    List<JobType> findByName(String name);
}
