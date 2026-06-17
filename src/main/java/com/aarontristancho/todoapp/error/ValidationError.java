package com.aarontristancho.todoapp.error;

// Represents a single validation error for a specific field.

public class ValidationError {

    private String field;
    private String message;

    // Constructor - empty
    public ValidationError() {}

    // Constructor with parameters
    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    // Getters & Setters
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
