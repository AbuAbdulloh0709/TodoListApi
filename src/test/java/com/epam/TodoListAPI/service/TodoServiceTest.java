package com.epam.TodoListAPI.service;

import com.epam.TodoListAPI.model.Todo;
import com.epam.TodoListAPI.repository.TodoRepository;
import com.epam.TodoListAPI.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTodos() {
        when(todoRepository.findAll()).thenReturn(Collections.singletonList(new Todo()));

        assertEquals(1, todoService.getAllTodos().size());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    void getTodoById() {
        when(todoRepository.findById(any())).thenReturn(Optional.of(new Todo()));

        assertEquals(new Todo(), todoService.getTodoById(1L));
        verify(todoRepository, times(1)).findById(any());
    }

    @Test
    void createTodo() {
        Todo todo = new Todo();
        when(todoRepository.save(any())).thenReturn(todo);

        assertEquals(todo, todoService.createTodo(new Todo()));
        verify(todoRepository, times(1)).save(any());
    }

    @Test
    void updateTodo() {
        Todo existingTodo = new Todo();
        when(todoRepository.findById(any())).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(any())).thenReturn(existingTodo);

        assertEquals(existingTodo, todoService.updateTodo(1L, new Todo()));
        verify(todoRepository, times(1)).findById(any());
        verify(todoRepository, times(1)).save(any());
    }

    @Test
    void deleteTodo() {
        doNothing().when(todoRepository).deleteById(any());

        todoService.deleteTodo(1L);
        verify(todoRepository, times(1)).deleteById(any());
    }
}
