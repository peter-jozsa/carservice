package hu.unideb.inf.lev.carservice.service.exception;

import java.lang.reflect.Field;

public class InvalidFieldValueException extends ValidationException {
    public InvalidFieldValueException(Field field) {
        this(field.getDeclaringClass(), field.getName());
    }

    public InvalidFieldValueException(Class clazz, String fieldName) {
        super("Value of " + clazz.getName() + "." + fieldName + " is invalid!");
    }
}
