package hu.unideb.inf.lev.carservice.service.exception;

/**
 * An exception class which describes an error when a field value of an instance is
 * determined to be empty.
 */
public class EmptyFieldValueException extends ValidationException {
    /**
     * Initializes the exception with a default error message referring
     * the given class and its field.
     * @param clazz The class of problematic instance
     * @param fieldName The name of the field which is found to be empty.
     */
    public EmptyFieldValueException(Class clazz, String fieldName) {
        super("Value of " + clazz.getName() + "." + fieldName + " is empty!");
    }
}
