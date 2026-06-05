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

    public Optional<Todo> findById(Long id) {
        for (Todo todo : todos) {
            if (todo.getId().equals(id)) {
                return Optional.of(todo);
            }
        }
        return Optional.empty();
    }

}
