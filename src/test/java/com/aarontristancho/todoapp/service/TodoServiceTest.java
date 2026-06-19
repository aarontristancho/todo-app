package com.aarontristancho.todoapp.service;

import com.aarontristancho.todoapp.exception.TodoNotFoundException;
import com.aarontristancho.todoapp.model.Todo;
import com.aarontristancho.todoapp.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

    private TodoRepository todoRepository;
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        todoRepository = Mockito.mock(TodoRepository.class);
        todoService = new TodoService(todoRepository);
    }

    @Test
    void shouldReturnTodoWhenTodoIsFound() {
        // ARRANGE
        Long id = 1L;
        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle("Test todo");

        // When Service asks to Repository for id - then return the Todo
        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));

        // ACT
        Todo result = todoService.getTodoById(id);

        // ASSERT - Verify if return the todo
        assertEquals(todo,result);
    }

    @Test
    void shouldThrowExceptionWhenTodoDoesNotExist() {
        // ARRANGE
        Long id = 999L;

        // When Service asks to Repository for id - then return an empty Optional
        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        // ASSET WITH ACT - Verify if this exception is thrown
        assertThrows(TodoNotFoundException.class,
        () -> todoService.getTodoById(id), "Se espera TodoNotFoundException");
    }

}
