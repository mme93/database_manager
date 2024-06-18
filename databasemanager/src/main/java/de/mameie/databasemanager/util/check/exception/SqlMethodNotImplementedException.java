package de.mameie.databasemanager.util.check.exception;

public class SqlMethodNotImplementedException extends RuntimeException{


    public SqlMethodNotImplementedException(String message) {
        super(message);
    }

    public SqlMethodNotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }
}
