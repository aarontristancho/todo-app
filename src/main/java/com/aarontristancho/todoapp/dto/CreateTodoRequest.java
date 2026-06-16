package com.aarontristancho.todoapp.dto;

import com.aarontristancho.todoapp.model.enums.Priority;
import jakarta.validation.constraints.NotBlank;

public class CreateTodoRequest {

    @NotBlank
    private String title;
    private String note;
    private String category;
    private Priority priority;

    // Empty constructor
    public CreateTodoRequest() {
    }

    // Constructor with parameters
    public CreateTodoRequest(String title, String note, String category, Priority priority) {
        this.title = title;
        this.note = note;
        this.category = category;
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
