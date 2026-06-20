package com.aarontristancho.todoapp.controller;

import com.aarontristancho.todoapp.dto.CreateTodoRequest;
import com.aarontristancho.todoapp.dto.UpdateTodoRequest;
import com.aarontristancho.todoapp.exception.TodoNotFoundException;
import com.aarontristancho.todoapp.model.Todo;
import com.aarontristancho.todoapp.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    // getTodoById
    @Test
    void shouldReturnTodoWhenTodoExists() throws Exception {
        // ARRANGE
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Todo Test");

        when(todoService.getTodoById(1L)).thenReturn(todo);

        //ACT + ASSERT
        mockMvc.perform(get("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Todo Test"));

    }

    @Test
    void shouldReturn404WhenTodoDoesNotExist() throws Exception {
        // ARRANGE
        Long id = 999L;

        when(todoService.getTodoById(id))
                .thenThrow(new TodoNotFoundException(id));

        // ACT + ASSERT
        mockMvc.perform(get("/todos/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Todo with id " + id + " not found"));
    }

    // createTodo
    @Test
    void shouldCreateTodoSuccessfully() throws Exception {
        // ARRANGE
        Todo createdTodo = new Todo();
        createdTodo.setId(1L);
        createdTodo.setTitle("Title Test");

        when(todoService.createTodo(any(CreateTodoRequest.class)))
                .thenReturn(createdTodo);

        String requestBody = """
            {
                "title": "Title Test"
            }
            """;

        // ACT + ASSERT
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Title Test"));
    }

    @Test
    void shouldReturn400WhenTitleIsBlank() throws Exception {
        // ARRANGE
        String requestBody = """
            {
                "title": ""
            }
            """;

        // ACT + ASSERT
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field").value("title"));
    }

    // updateTodo
    @Test
    void shouldUpdateTodoSuccessfully() throws Exception {
        // ARRANGE
        Long id = 1L;
        Todo updatedTodo = new Todo();
        updatedTodo.setId(id);
        updatedTodo.setTitle("Updated title");

        when(todoService.updateTodo(eq(id), any(UpdateTodoRequest.class)))
                .thenReturn(updatedTodo);

        String requestBody = """
            {
                "title": "Updated title"
            }
            """;

        // ACT + ASSERT
        mockMvc.perform(put("/todos/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated title"));
    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingTodo() throws Exception {
        // ARRANGE
        Long id = 999L;

        when(todoService.updateTodo(eq(id), any(UpdateTodoRequest.class)))
                .thenThrow(new TodoNotFoundException(id));

        String requestBody = """
            {
                "title": "Updated title"
            }
            """;

        // ACT + ASSERT
        mockMvc.perform(put("/todos/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Todo with id " + id + " not found"));
    }

    // deleteTodo
    @Test
    void shouldDeleteTodoSuccessfully() throws Exception {
        // ARRANGE
        Long id = 1L;

        doNothing().when(todoService).deleteTodo(id);

        // ACT + ASSERT
        mockMvc.perform(delete("/todos/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404WhenDeletingNonExistingTodo() throws Exception {
        // ARRANGE
        Long id = 999L;

        doThrow(new TodoNotFoundException(id))
                .when(todoService)
                .deleteTodo(id);

        // ACT + ASSERT
        mockMvc.perform(delete("/todos/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Todo with id " + id + " not found"));
    }

}
