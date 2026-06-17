package com.aarontristancho.todoapp.controller;

import com.aarontristancho.todoapp.error.ValidationError;
import com.aarontristancho.todoapp.error.ValidationErrorResponse;
import com.aarontristancho.todoapp.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

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

    // Translate this validation exception to an HTTP response 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleTodoInvalidData(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ValidationError> validationErrors = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            ValidationError validationError;
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            validationError = new ValidationError(field, message);
            validationErrors.add(validationError);
        }

        ValidationErrorResponse response = new ValidationErrorResponse(validationErrors);
        return ResponseEntity.badRequest().body(response);

    }

}
