package com.aarontristancho.todoapp.controller;

import com.aarontristancho.todoapp.exception.TodoInvalidDataException;
import com.aarontristancho.todoapp.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Class to transform Java exceptions to HTTP responses (404, 200,...)
@ControllerAdvice
public class GlobalExceptionHandler {

    // Translate this exception to an HTTP response 404
    @ExceptionHandler(TodoNotFoundException.class) //Annotation - When this exception occurs, execute the below method
    public ResponseEntity<String> handleTodoNotFound(TodoNotFoundException ex) { //ResponseEntity represents the entire HTTP response
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    // Translate this exception to an HTTP response 400
    @ExceptionHandler(TodoInvalidDataException.class)
    public ResponseEntity<String> handleTodoInvalidData(TodoInvalidDataException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

}
