package ru.danilscheglov.exception;

/**
 * @author Danil Scheglov
 */
public class TodoApplicationException extends RuntimeException {

    public TodoApplicationException(String message) {
        super(message);
    }
}
