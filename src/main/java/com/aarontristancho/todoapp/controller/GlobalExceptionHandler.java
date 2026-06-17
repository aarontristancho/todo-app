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

// Transforms Java exceptions to HTTP responses

@ControllerAdvice
public class GlobalExceptionHandler {

    /* Executed when a requested Todo cannot be found.
       Converts the custom business exception into an HTTP 404 response
       so the client receives a meaningful error instead of an internal server error.
     */
    @ExceptionHandler(TodoNotFoundException.class) //Annotation - When this exception occurs, execute the below method
    public ResponseEntity<String> handleTodoNotFound(TodoNotFoundException ex) { //ResponseEntity represents the entire HTTP response
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    /* Handles validation errors triggered by @Valid annotations.
       Extracts all invalid fields and their error messages, then
       returns a structured 400 Bad Request response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleTodoInvalidData(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ValidationError> validationErrors = new ArrayList<>();

        // Convert each Spring FieldError into the application's custom ValidationError format.
        for (FieldError fieldError : fieldErrors) {
            ValidationError validationError;
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            validationError = new ValidationError(field, message);
            validationErrors.add(validationError);
        }

        // Wrap all validation errors in a response DTO and send them back with HTTP 400 (Bad Request).
        ValidationErrorResponse response = new ValidationErrorResponse(validationErrors);
        return ResponseEntity.badRequest().body(response);

    }

}
