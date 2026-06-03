package com.aarontristancho.todoapp.service;

import com.aarontristancho.todoapp.exception.TodoInvalidDataException;
import com.aarontristancho.todoapp.exception.TodoNotFoundException;
import com.aarontristancho.todoapp.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();
    private Long nextId = 3L;

    public TodoService() {
        todos.add(new Todo(1L, "Learn Spring Boot", false));
        todos.add(new Todo(2L, "Build a REST API", false));
    }

    //GET All - get the whole "to do" list
    public List<Todo> getAllTodos() {
        return todos;
    }

    //GET - get only one "to do" by id
    public Todo getTodoById(Long id) {
        for (int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            if (todo.getId().equals(id)) {
                return todo;
            }
        }
        throw new TodoNotFoundException(id);
    }

    //POST - creat a new "to do"
    public Todo createTodo(Todo todo) {
        validateTodo(todo); // First check if title is null
        Todo newTodo = new Todo(nextId, todo.getTitle(), false);
        todos.add(newTodo);
        nextId++;
        return newTodo;
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
        Todo todo = getTodoById(id);
        todos.remove(todo);
    }

    // Validate if title is null
    private void validateTodo(Todo todo) {
        if (todo.getTitle() == null) {
            throw new TodoInvalidDataException("Title cannot be null");
        }
    }

}
