package com.test.test.controller;

import com.test.test.dto.ResponseDTO;
import com.test.test.dto.TaskDTO;
import com.test.test.model.TaskStatus;
import com.test.test.service.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ControllerTest {

    @Mock
    private TestService testService;

    @InjectMocks
    private Controller controller;

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

        ResponseDTO responseDTO = new ResponseDTO(1L, "Test Task", "Test Description", TaskStatus.TODO);
        when(testService.createTask(any(TaskDTO.class))).thenReturn(responseDTO);

        ResponseEntity<ResponseDTO> response = controller.createTask(taskDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void getAllTasks() {
        ResponseDTO responseDTO = new ResponseDTO(1L, "Test Task", "Test Description", TaskStatus.TODO);
        when(testService.getAllTasks()).thenReturn(Collections.singletonList(responseDTO));

        ResponseEntity<List<ResponseDTO>> response = controller.getAllTasks();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getTask() {
        ResponseDTO responseDTO = new ResponseDTO(1L, "Test Task", "Test Description", TaskStatus.TODO);
        when(testService.getTaskById(1L)).thenReturn(responseDTO);

        ResponseEntity<ResponseDTO> response = controller.getTask(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void updateTask() {
        TaskDTO taskDto = new TaskDTO();
        taskDto.setTitle("Updated Task");
        taskDto.setDescription("Updated Description");
        taskDto.setStatus(TaskStatus.IN_PROGRESS);

        ResponseDTO responseDTO = new ResponseDTO(1L, "Updated Task", "Updated Description", TaskStatus.IN_PROGRESS);
        when(testService.updateTask(1L, taskDto)).thenReturn(responseDTO);

        ResponseEntity<ResponseDTO> response = controller.updateTask(1L, taskDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void deleteTask() {
        doNothing().when(testService).deleteTask(1L);

        ResponseEntity<Void> response = controller.deleteTask(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
    }
}