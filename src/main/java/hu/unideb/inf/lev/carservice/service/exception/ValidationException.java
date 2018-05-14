package hu.unideb.inf.lev.carservice.service.exception;

/**
 * The abstract class of exceptions, which describe an entity validation error.
 */
public abstract class ValidationException extends Exception {
    /**
     * Initializes the exception with a provided error message.
     * @param message A human-readable error message.
     */
    public ValidationException(String message) {
        super(message);
    }
}
