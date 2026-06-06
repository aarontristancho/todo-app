package com.aarontristancho.todoapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aarontristancho.todoapp.model.Todo;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {

    private List<Todo> todos = new ArrayList<>();
    private Long nextId = 1L;

    // Get the whole "to do" List
    public List<Todo> findAll() {
        return todos;
    }

    // Get only one "to do" by id
    public Optional<Todo> findById(Long id) {
        for (Todo todo : todos) {
            if (todo.getId().equals(id)) {
                return Optional.of(todo);
            }
        }
        return Optional.empty();
    }

    // Set the id and save the "to do" into the list
    public Todo save(Todo todo) {
        todo.setId(nextId);
        todos.add(todo);
        nextId++;
        return todo;
    }

    public void deleteById(Long id) {
        Optional<Todo> todo = findById(id);
        todos.remove(todo.get());
    }

}
