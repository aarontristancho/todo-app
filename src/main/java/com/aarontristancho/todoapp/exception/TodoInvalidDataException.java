package com.aarontristancho.todoapp.exception;

public class TodoInvalidDataException extends RuntimeException {

    // Exception when data introduced by client is not valid with our service rules
    public TodoInvalidDataException(String message) {
        super(message);
    }

}
