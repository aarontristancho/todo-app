package com.aarontristancho.todoapp.service;

import com.aarontristancho.todoapp.exception.TodoInvalidDataException;
import com.aarontristancho.todoapp.exception.TodoNotFoundException;
import com.aarontristancho.todoapp.model.Todo;
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

    //GET All - Get from Repository the whole "to do" list
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
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
        validateTodo(todo); // First check if title is null
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    //PUT - modify an entire "to do"
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo todo = getTodoById(id); // First check if this id exist
        validateTodo(updatedTodo); // Then check if title is null
        todo.setTitle(updatedTodo.getTitle());
        todo.setCompleted(updatedTodo.isCompleted());
        return todo;
    }

    //PATCH - modify an attribute of a "to do". Make it completed
    public Todo completeTodo(Long id) {
        Todo todo = getTodoById(id);
        todo.setCompleted(true);
        return todo;
    }

    //DELETE - delete a "to do" from the list
    public void deleteTodo(Long id) {
        getTodoById(id);
        todoRepository.deleteById(id);
    }

    // Validate if title is null
    private void validateTodo(Todo todo) {
        if (todo.getTitle() == null) {
            throw new TodoInvalidDataException("Title cannot be null");
        }
    }

}
