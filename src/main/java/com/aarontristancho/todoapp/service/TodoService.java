package com.aarontristancho.todoapp.service;

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

    //GET All - Get from Repository the whole "to do" list with filters
    public List<Todo> getAllTodos(String category, Status status) {
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

    //GET - Get from Repository a "to do" by id
    public Todo getTodoById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            return todo.get();
        }
        throw new TodoNotFoundException(id);
    }

    //POST - Create a new "to do"
    public Todo createTodo(Todo todo) {
        if (todo.getStatus() == null) {
            todo.setStatus(Status.PENDING);
        }
        if (todo.getPriority() == null) {
            todo.setPriority(Priority.MEDIUM);
        }
        return todoRepository.save(todo);  // Save this "to do" in persistance
    }

    //PUT - modify an entire "to do"
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo todo = getTodoById(id); // First check if this id exist
        if (updatedTodo.getTitle() != null) {
            todo.setTitle(updatedTodo.getTitle());
        }
        if (updatedTodo.getNote() != null) {
            todo.setNote(updatedTodo.getNote());
        }
        if (updatedTodo.getCategory() != null) {
            todo.setCategory(updatedTodo.getCategory());
        }
        if (updatedTodo.getStatus() != null) {
            todo.setStatus(updatedTodo.getStatus());
        }
        if (updatedTodo.getPriority() != null) {
            todo.setPriority(updatedTodo.getPriority());
        }
        return todoRepository.save(todo);
    }

    //DELETE - delete a "to do" from the list
    public void deleteTodo(Long id) {
        getTodoById(id);
        todoRepository.deleteById(id);
    }

}
