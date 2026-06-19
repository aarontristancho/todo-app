package com.aarontristancho.todoapp.service;

import com.aarontristancho.todoapp.dto.CreateTodoRequest;
import com.aarontristancho.todoapp.dto.UpdateTodoRequest;
import com.aarontristancho.todoapp.exception.TodoNotFoundException;
import com.aarontristancho.todoapp.model.Todo;
import com.aarontristancho.todoapp.model.enums.Priority;
import com.aarontristancho.todoapp.model.enums.Status;
import com.aarontristancho.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    // Attribute
    private final TodoRepository todoRepository;

    // Constructor (injecting TodoRepository)
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // GET All - Retrieves todos applying the optional category and status filters.
    // If no filters are provided, all todos are returned.
    public List<Todo> getAllTodos(String category, Status status) {
        // Select the appropriate repository query depending on which optional filters were provided.
        if (category == null && status == null) {
            return todoRepository.findAll();
        } else if (category != null && status == null) {
            return todoRepository.findByCategoryIgnoreCase(category);
        } else if (category == null && status != null) {
            return todoRepository.findByStatus(status);
        } else {
            return todoRepository.findByCategoryIgnoreCaseAndStatus(category, status);
        }
    }

    // GET - Retrieve a todo by id or throw TodoNotFoundException if not found.
    public Todo getTodoById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            return todo.get();
        }
        throw new TodoNotFoundException(id);
    }

    // POST - Creates a new todo from the request data.
    // New todos are always created with PENDING status and MEDIUM priority when no priority is provided.
    public Todo createTodo(CreateTodoRequest request) { // "request" as a DTO object, transfers to todo entity
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setNote(request.getNote());
        todo.setCategory(request.getCategory());
        todo.setStatus(Status.PENDING);
        if (request.getPriority() == null) {
            todo.setPriority(Priority.MEDIUM);
        } else {
            todo.setPriority(request.getPriority());
        }
        return todoRepository.save(todo);
    }

    // PUT - Updates only the fields provided in the request.
    // Existing values are preserved when a field is not supplied.
    public Todo updateTodo(Long id, UpdateTodoRequest request) {
        Todo todo = getTodoById(id); // First check if this id exist
        // Apply only the fields explicitly provided by the client.
        if (request.getTitle() != null) {
            todo.setTitle(request.getTitle());
        }
        if (request.getNote() != null) {
            todo.setNote(request.getNote());
        }
        if (request.getCategory() != null) {
            todo.setCategory(request.getCategory());
        }
        if (request.getStatus() != null) {
            todo.setStatus(request.getStatus());
        }
        if (request.getPriority() != null) {
            todo.setPriority(request.getPriority());
        }
        return todoRepository.save(todo);
    }

    // DELETE - Deletes a todo by its identifier.
    // Throws TodoNotFoundException if the todo does not exist.
    public void deleteTodo(Long id) {
        getTodoById(id);
        todoRepository.deleteById(id);
    }

}
