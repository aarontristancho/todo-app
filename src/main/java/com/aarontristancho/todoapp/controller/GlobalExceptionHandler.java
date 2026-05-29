package com.aarontristancho.todoapp.controller;

import com.aarontristancho.todoapp.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Class to transform Java exceptions to HTTP responses (404, 200,...)
@ControllerAdvice
public class GlobalExceptionHandler {

    //ResponseEntity represents the entire HTTP response
    @ExceptionHandler(TodoNotFoundException.class) //Annotation - When this exception occurs, execute the below method
    public ResponseEntity<String> handleTodoNotFound(TodoNotFoundException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

}
