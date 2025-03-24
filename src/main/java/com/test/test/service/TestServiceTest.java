package com.test.test.service;

import com.test.test.dto.ResponseDTO;
import com.test.test.dto.TaskDTO;
import com.test.test.model.Task;
import com.test.test.model.TaskStatus;
import com.test.test.repository.Rep;
import com.test.test.dto.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TestServiceTest {

    @Mock
    private Rep rep;

    @InjectMocks
    private TestService testService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask() {
        TaskDTO taskDto = new TaskDTO();
        taskDto.setTitle("Test Task");
        taskDto.setDescription("Test Description");
        taskDto.setStatus(TaskStatus.TODO);

        Task task = Converter.toEntity(taskDto);
        task.setId(1L);

        when(rep.save(any(Task.class))).thenReturn(task);

        ResponseDTO response = testService.createTask(taskDto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Task", response.getTitle());
        assertEquals("Test Description", response.getDescription());
        assertEquals(TaskStatus.TODO, response.getStatus());
    }

    @Test
    void getAllTasks() {
        Task task = new Task(1L, "Test Task", "Test Description", TaskStatus.TODO);
        when(rep.findAll()).thenReturn(Collections.singletonList(task));

        List<ResponseDTO> tasks = testService.getAllTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals(1L, tasks.get(0).getId());
    }

    @Test
    void getTaskById() {
        Task task = new Task(1L, "Test Task", "Test Description", TaskStatus.TODO);
        when(rep.findById(1L)).thenReturn(Optional.of(task));

        ResponseDTO response = testService.getTaskById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Task", response.getTitle());
    }

    @Test
    void updateTask() {
        TaskDTO taskDto = new TaskDTO();
        taskDto.setTitle("Updated Task");
        taskDto.setDescription("Updated Description");
        taskDto.setStatus(TaskStatus.IN_PROGRESS);

        Task task = new Task(1L, "Test Task", "Test Description", TaskStatus.TODO);
        when(rep.findById(1L)).thenReturn(Optional.of(task));
        when(rep.save(any(Task.class))).thenReturn(task);

        ResponseDTO response = testService.updateTask(1L, taskDto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Updated Task", response.getTitle());
        assertEquals("Updated Description", response.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, response.getStatus());
    }

    @Test
    void deleteTask() {
        when(rep.existsById(1L)).thenReturn(true);
        doNothing().when(rep).deleteById(1L);

        testService.deleteTask(1L);

        verify(rep, times(1)).deleteById(1L);
    }
}