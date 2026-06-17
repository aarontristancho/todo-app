package com.aarontristancho.todoapp.repository;

import com.aarontristancho.todoapp.model.Todo;
import com.aarontristancho.todoapp.model.enums.Priority;
import com.aarontristancho.todoapp.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data JPA repository for Todo entities.
// Provides CRUD operations and custom query methods.

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByCategoryIgnoreCase(String category);
    List<Todo> findByStatus(Status status);
    List<Todo> findByCategoryIgnoreCaseAndStatus(String category, Status status);

}
