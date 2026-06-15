package com.aarontristancho.todoapp.error;

import java.util.List;

public class ValidationErrorResponse {

    private List<ValidationError> errors;

    // Constructor - empty
    public ValidationErrorResponse() {}

    // Constructor with parameters
    public ValidationErrorResponse(List<ValidationError> errors) {
        this.errors = errors;
    }

    // Getters & Setters
    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}
