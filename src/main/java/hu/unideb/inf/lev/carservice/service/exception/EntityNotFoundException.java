package hu.unideb.inf.lev.carservice.service.exception;

/**
 * An exception class which describes an error when a requested entity could not
 * be found.
 */
public class EntityNotFoundException extends Exception {
    /**
     * Initializes the exception.
     * @param message A human-readable error message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}