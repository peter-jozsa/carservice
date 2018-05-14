package hu.unideb.inf.lev.carservice.service.exception;

/**
 * An exception class which describes an error when a field value of an instance has an invalid value.
 */
public class InvalidFieldValueException extends ValidationException {
    /**
     * Initializes the exception with a default error message referring
     * the given class and its field.
     * @param clazz The class of problematic instance
     * @param fieldName The name of the field which is found to be invalid.
     */
    public InvalidFieldValueException(Class clazz, String fieldName) {
        super("Value of " + clazz.getName() + "." + fieldName + " is invalid!");
    }
}
