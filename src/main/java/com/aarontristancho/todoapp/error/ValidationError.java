package com.aarontristancho.todoapp.error;

// Represents a single validation error for a specific field.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ValidationError {

    private String field;
    private String message;

}
