package com.aarontristancho.todoapp.dto;

import com.aarontristancho.todoapp.model.enums.Priority;
import com.aarontristancho.todoapp.model.enums.Status;

// Request DTO used to update an existing todo.
// Only provided fields will be modified.

public class UpdateTodoRequest {

    private String title;
    private String note;
    private String category;
    private Status status;
    private Priority priority;

    // Empty constructor
    public UpdateTodoRequest() {
    }

    // Constructor with parameters
    public UpdateTodoRequest(String title, String note, String category, Status status, Priority priority) {
        this.title = title;
        this.note = note;
        this.category = category;
        this.status = status;
        this.priority = priority;
    }

    // Setters&Getters
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
}
