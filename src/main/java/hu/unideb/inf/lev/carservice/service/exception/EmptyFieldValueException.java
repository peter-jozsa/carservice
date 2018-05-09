package hu.unideb.inf.lev.carservice.service.exception;

import java.lang.reflect.Field;

public class EmptyFieldValueException extends ValidationException {
    public EmptyFieldValueException(Field field) {
        this(field.getDeclaringClass(), field.getName());
    }

    public EmptyFieldValueException(Class clazz, String fieldName) {
        super("Value of " + clazz.getName() + "." + fieldName + " is empty!");
    }
}
