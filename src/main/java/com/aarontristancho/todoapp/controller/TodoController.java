package com.aarontristancho.todoapp.controller;

import com.aarontristancho.todoapp.dto.CreateTodoRequest;
import com.aarontristancho.todoapp.dto.UpdateTodoRequest;
import com.aarontristancho.todoapp.model.Todo;
import com.aarontristancho.todoapp.model.enums.Status;
import com.aarontristancho.todoapp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //End Points
    @GetMapping
    public List<Todo> getAllTodos(@RequestParam(value = "category", required = false) String category,
                                    @RequestParam(value = "status", required = false) Status status) {
        return todoService.getAllTodos(category, status);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody CreateTodoRequest request) {
        Todo createdTodo = todoService.createTodo(request);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        Todo updatedTodo = todoService.updateTodo(id, request);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
