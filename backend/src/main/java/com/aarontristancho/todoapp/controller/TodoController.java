package com.aarontristancho.todoapp.controller;

import com.aarontristancho.todoapp.dto.CreateTodoRequest;
import com.aarontristancho.todoapp.dto.UpdateTodoRequest;
import com.aarontristancho.todoapp.error.ValidationErrorResponse;
import com.aarontristancho.todoapp.model.Todo;
import com.aarontristancho.todoapp.model.enums.Status;
import com.aarontristancho.todoapp.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation( // Description of HTTP request in Swagger
            summary = "Create a new task",
            description = "Creates a new todo item.\n" +
                    "\n" +
                    "The status is automatically initialized as PENDING.\n" +
                    "If no priority is provided, MEDIUM is assigned by default."
    )
    @ApiResponses(value = { // Description of all HTTP codes in Swagger
            @ApiResponse(responseCode = "201",
                        description = "Task created successfully"),
            @ApiResponse(responseCode = "400",
                        description = "Validation failed",
                        content = @Content(
                                schema = @Schema(
                                        implementation = ValidationErrorResponse.class
                                )
                        )
            )
    })
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
