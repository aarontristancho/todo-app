package com.aarontristancho.todoapp.model;

import com.aarontristancho.todoapp.model.enums.Priority;
import com.aarontristancho.todoapp.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Represents a task in the Todo application.

@Getter @Setter
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String note;
    private String category;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Priority priority;

}
