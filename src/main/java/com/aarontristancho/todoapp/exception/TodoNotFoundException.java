package com.aarontristancho.todoapp.exception;

public class TodoNotFoundException extends RuntimeException {

    //Exception when the requested id does not exist
    public TodoNotFoundException(Long id) {
        super("Todo with id " + id + " not found");
    }

}
