package com.aarontristancho.todoapp.dto;

import com.aarontristancho.todoapp.model.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Request DTO used to create a new todo.
// Contains the data provided by the client when creating a task.

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CreateTodoRequest {

    @NotBlank
    private String title;
    private String note;
    private String category;
    private Priority priority;

}
