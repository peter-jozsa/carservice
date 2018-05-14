package hu.unideb.inf.lev.carservice.service;

import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.util.List;

/**
 * This interface describes the look of the service which manages {@link JobType} entities.
 */
public interface JobTypeService {
    /**
     * Gets a job type entity by its unique identifier.
     * @param id Unique identifier of the job entity.
     * @return A job type entity with same id as requested or <code>null</code> if it could not be found.
     */
    JobType getJobTypeById(Long id);

    /**
     * Creates a new job type entity.
     * @param jobType A job type entity
     * @throws ValidationException When the given job type entity is not complete.
     */
    void createJobType(JobType jobType) throws ValidationException;

    /**
     * Updates a job type entity.
     * @param jobType A job type entity
     * @throws ValidationException When the given entity is not valid.
     * @throws EntityNotFoundException When the given entity could not be loaded from the database.
     */
    void updateJobType(JobType jobType) throws ValidationException, EntityNotFoundException;

    /**
     * Deletes a job type entity by its unique identifier.
     * @param id Unique identifier
     * @throws EntityNotFoundException When the entity could not be loaded form the database.
     */
    void deleteJobTypeById(Long id) throws EntityNotFoundException;

    /**
     * Validates a job type entity.
     * @param jobType An entity to be validated.
     * @return <code>true</code> if validation was successful.
     * @throws ValidationException When the given entity is invalid.
     */
    boolean validateJobType(JobType jobType) throws ValidationException;

    /**
     * Gets all job type entities.
     * @return A list of job entities.
     */
    List<JobType> getAllJobType();

    /**
     * Gets all job type entities matching the provided string.
     * @param str A string value
     * @return A list of job type entities matching the criteria.
     */
    List<JobType> textSearchJobType(String str);
}
