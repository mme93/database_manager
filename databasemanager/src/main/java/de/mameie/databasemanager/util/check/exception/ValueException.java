package de.mameie.databasemanager.util.check.exception;

public class ValueException extends RuntimeException {

    public ValueException(String message) {
        super(message);
    }

    public ValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
