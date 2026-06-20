package com.aarontristancho.todoapp.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// Response DTO containing all validation errors detected during request processing.

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ValidationErrorResponse {

    private List<ValidationError> errors;

}
