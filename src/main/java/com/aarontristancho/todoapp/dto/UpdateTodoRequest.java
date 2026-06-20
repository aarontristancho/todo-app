package com.aarontristancho.todoapp.dto;

import com.aarontristancho.todoapp.model.enums.Priority;
import com.aarontristancho.todoapp.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Request DTO used to update an existing todo.
// Only provided fields will be modified.

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UpdateTodoRequest {

    private String title;
    private String note;
    private String category;
    private Status status;
    private Priority priority;

}
