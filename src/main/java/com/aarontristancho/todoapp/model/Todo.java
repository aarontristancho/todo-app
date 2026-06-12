package com.aarontristancho.todoapp.model;

import com.aarontristancho.todoapp.model.enums.Priority;
import com.aarontristancho.todoapp.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String title;
    private String note;
    private String category;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Priority priority;


    // Empty constructor
    public Todo() {
    }

    // Setters&Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /*
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    */
}
