package com.aarontristancho.todoapp.exception;

// Custom exception used when an operation references
// a todo that does not exist in the database.

public class TodoNotFoundException extends RuntimeException {

    //Exception when the requested id does not exist
    public TodoNotFoundException(Long id) {
        super("Todo with id " + id + " not found");
    }

}
