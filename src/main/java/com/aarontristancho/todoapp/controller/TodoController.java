package com.aarontristancho.todoapp.controller;

import com.aarontristancho.todoapp.model.Todo;
import com.aarontristancho.todoapp.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PatchMapping("/{id}/complete")
    public Todo completeTodo(@PathVariable Long id) {
        return todoService.completeTodo(id);
    }

    @DeleteMapping("/{id}")
    public Todo deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }

}
