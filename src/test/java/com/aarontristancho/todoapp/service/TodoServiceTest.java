package com.aarontristancho.todoapp.service;

import com.aarontristancho.todoapp.dto.CreateTodoRequest;
import com.aarontristancho.todoapp.dto.UpdateTodoRequest;
import com.aarontristancho.todoapp.exception.TodoNotFoundException;
import com.aarontristancho.todoapp.model.Todo;
import com.aarontristancho.todoapp.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.aarontristancho.todoapp.model.enums.Priority.HIGH;
import static com.aarontristancho.todoapp.model.enums.Priority.MEDIUM;
import static com.aarontristancho.todoapp.model.enums.Status.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

    private TodoRepository todoRepository;
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        todoRepository = Mockito.mock(TodoRepository.class);
        todoService = new TodoService(todoRepository);
    }

    // getAllTodos Tests
    @Test
    void shouldReturnAllTodosWhenNoFiltersProvided() {
        // ARRANGE
        List<Todo> todos = List.of(new Todo(), new Todo());

        when(todoRepository.findAll()).thenReturn(todos);

        // ACT
        List<Todo> result = todoService.getAllTodos(null, null);

        // ASSERT
        assertEquals(todos, result);
        verify(todoRepository).findAll();
    }

    @Test
    void shouldReturnTodosFilteredByCategory() {
        // ARRANGE
        String category = "Category Test";
        List<Todo> todos = List.of(new Todo());

        when(todoRepository.findByCategoryIgnoreCase(category)).thenReturn(todos);

        // ACT
        List<Todo> result = todoService.getAllTodos(category, null);

        // ASSERT
        assertEquals(todos, result);
        verify(todoRepository).findByCategoryIgnoreCase(category);
    }

    @Test
    void shouldReturnTodosFilteredByStatus() {
        // ARRANGE
        List<Todo> todos = List.of(new Todo());

        when(todoRepository.findByStatus(PENDING)).thenReturn(todos);

        // ACT
        List<Todo> result = todoService.getAllTodos(null, PENDING);

        // ASSERT
        assertEquals(todos, result);
        verify(todoRepository).findByStatus(PENDING);
    }

    @Test
    void shouldReturnTodosFilteredByCategoryAndStatus() {
        // ARRANGE
        String category = "Category Test";
        List<Todo> todos = List.of(new Todo());

        when(todoRepository.findByCategoryIgnoreCaseAndStatus(category, PENDING))
                .thenReturn(todos);

        // ACT
        List<Todo> result = todoService.getAllTodos(category, PENDING);

        // ASSERT
        assertEquals(todos, result);
        verify(todoRepository).findByCategoryIgnoreCaseAndStatus(category, PENDING);
    }

    // getTodoById Test
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
    void shouldThrowExceptionWhenGettingNonExistingTodo() {
        // ARRANGE
        Long id = 999L;

        // When Service asks to Repository for id - then return an empty Optional
        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        // ASSERT - Verify if this exception is thrown
        assertThrows(TodoNotFoundException.class,
                () -> todoService.getTodoById(id), "TodoNotFoundException is expected.");
    }

    // createTodo Tests
    @Test
    void shouldCreateTodoWithProvidedPriority() {
        // ARRANGE
        CreateTodoRequest request = new CreateTodoRequest();
        request.setTitle("Todo Test");
        request.setCategory("Todo Category");
        request.setPriority(HIGH);

        when(todoRepository.save(any(Todo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        Todo result = todoService.createTodo(request);

        // ASSERT
        assertEquals("Todo Test", result.getTitle());
        assertEquals("Todo Category", result.getCategory());
        assertEquals(HIGH, result.getPriority());
        assertEquals(PENDING, result.getStatus());

    }

    @Test
    void shouldAssignMediumPriorityWhenPriorityIsNotProvided() {
        // ARRANGE
        CreateTodoRequest request = new CreateTodoRequest();
        request.setTitle("Todo Test");
        request.setCategory("Todo Category");

        when(todoRepository.save(any(Todo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //ACT
        Todo result = todoService.createTodo(request);

        // ASSERT
        assertEquals(MEDIUM, result.getPriority());
        assertEquals(PENDING, result.getStatus());
    }

    // updateTodo Tests
    @Test
    void shouldModifyOnlyNotNullParameters() {
        // ARRANGE
        Long id = 1L;

        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle("Todo test");
        todo.setCategory("Category Test");
        todo.setStatus(PENDING);

        UpdateTodoRequest request = new UpdateTodoRequest();
        request.setTitle("Todo test updated");
        request.setCategory(null);
        request.setStatus(null);

        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));
        when(todoRepository.save(todo)).thenReturn(todo);

        // ACT
        Todo result = todoService.updateTodo(id, request);

        // ASSERT
        assertEquals("Todo test updated", result.getTitle());
        assertEquals("Category Test", result.getCategory());
        assertEquals(PENDING, result.getStatus());

    }

    @Test
    void shouldNotModifyTodoWhenRequestIsEmpty() {
        // ARRANGE
        Long id = 1L;

        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle("Todo test");
        todo.setCategory("Category Test");
        todo.setStatus(PENDING);

        UpdateTodoRequest request = new UpdateTodoRequest();
        request.setTitle(null);
        request.setCategory(null);
        request.setStatus(null);

        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));
        when(todoRepository.save(todo)).thenReturn(todo);

        // ACT
        Todo result = todoService.updateTodo(id, request);

        // ASSERT
        assertEquals("Todo test", result.getTitle());
        assertEquals("Category Test", result.getCategory());
        assertEquals(PENDING, result.getStatus());

    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingTodo() {
        // ARRANGE
        Long id = 999L;

        UpdateTodoRequest request = new UpdateTodoRequest();

        // When Service asks to Repository for id - then return an empty Optional
        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        // ASSERT WITH ACT - Verify if this exception is thrown
        assertThrows(TodoNotFoundException.class,
                () -> todoService.updateTodo(id, request), "TodoNotFoundException is expected.");
    }

    // deleteTodo Tests
    @Test
    void shouldDeleteTodoWhenTodoExists() {
        // ARRANGE
        Long id = 1L;
        Todo todo = new Todo();
        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));

        // ACT
        todoService.deleteTodo(id);

        // ASSERT
        verify(todoRepository).findById(id);
        verify(todoRepository).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingTodo() {
        // ARRANGE
        Long id = 1L;
        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        // ASSERT
        assertThrows(TodoNotFoundException.class,
                () -> todoService.deleteTodo(id), "TodoNotFoundException is expected.");
    }

}
