package hu.unideb.inf.lev.carservice.service;

import hu.unideb.inf.lev.carservice.model.Worksheet;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.util.List;

/**
 * This interface describes the look of the service which manages {@link Worksheet} entities.
 */
public interface WorksheetService {
    /**
     * Validity time of a discount in seconds.
     */
    int DISCOUNT_VALIDITY_TIME = 30*24*60*60;

    /**
     * Maximum discount percentage value.
     */
    int DISCOUNT_MAX_VALUE = 40;

    /**
     * The step value when discount should be increased for a person.
     */
    int DISCOUNT_STEP_VALUE = 5;

    /**
     * Gets a worksheet entity by its unique identifier.
     * @param id Unique identifier of the worksheet entity.
     * @return A worksheet entity with same id as requested or <code>null</code> if it could not be found.
     */
    Worksheet getWorksheetById(Long id);

    /**
     * Creates a new worksheet entity.
     * @param worksheet A worksheet entity
     * @throws ValidationException When the given worksheet entity is not complete.
     */
    void createWorksheet(Worksheet worksheet) throws ValidationException;

    /**
     * Updates a worksheet entity.
     * @param worksheet A worksheet entity
     * @throws ValidationException When the given entity is not valid.
     * @throws EntityNotFoundException When the given entity could not be loaded from the database.
     */
    void updateWorksheet(Worksheet worksheet) throws ValidationException, EntityNotFoundException;

    /**
     * Deletes a worksheet entity by its unique identifier.
     * @param id Unique identifier
     * @throws EntityNotFoundException When the entity could not be loaded form the database.
     */
    void deleteWorksheetById(Long id) throws EntityNotFoundException;

    /**
     * Validates a worksheet entity.
     * @param worksheet An entity to be validated.
     * @return <code>true</code> if validation was successful.
     * @throws ValidationException When the given entity is invalid.
     */
    boolean validateWorksheet(Worksheet worksheet) throws ValidationException;

    /**
     * Gets all worksheet entities.
     * @return A list of worksheet entities.
     */
    List<Worksheet> getAllWorksheet();

    /**
     * Gets all worksheet entities matching the provided string.
     * @param str A string value
     * @return A list of worksheet entities matching the criteria.
     */
    List<Worksheet> textSearchWorksheet(String str);

    /**
     * Calculates the sum of job prices of the worksheet.
     * @param worksheet A worksheet entity.
     * @return The sum of job costs.
     */
    long calculateJobSum(Worksheet worksheet);

    /**
     * Calculates the total cost of the worksheet.
     * It also applies the discount.
     * @param worksheet A worksheet entity
     * @return The sum of job costs minus the discount.
     */
    long calculateTotal(Worksheet worksheet);
}
