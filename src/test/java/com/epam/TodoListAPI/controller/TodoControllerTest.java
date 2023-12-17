package com.epam.TodoListAPI.controller;

import com.epam.TodoListAPI.model.Todo;
import com.epam.TodoListAPI.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    public TodoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTodos() {
        when(todoService.getAllTodos()).thenReturn(Collections.singletonList(new Todo()));

        assertEquals(1, todoController.getAllTodos().size());
        verify(todoService, times(1)).getAllTodos();
    }

    @Test
    void getTodoById() {
        when(todoService.getTodoById(any())).thenReturn(new Todo());

        assertEquals(new Todo(), todoController.getTodoById(1L));
        verify(todoService, times(1)).getTodoById(any());
    }

    @Test
    void createTodo() {
        Todo todo = new Todo();
        when(todoService.createTodo(any())).thenReturn(todo);

        assertEquals(todo, todoController.createTodo(new Todo()));
        verify(todoService, times(1)).createTodo(any());
    }

    @Test
    void updateTodo() {
        Todo existingTodo = new Todo();
        when(todoService.updateTodo(any(), any())).thenReturn(existingTodo);

        assertEquals(existingTodo, todoController.updateTodo(1L, new Todo()));
        verify(todoService, times(1)).updateTodo(any(), any());
    }
}
