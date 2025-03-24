package com.test.test.controller;

import com.test.test.dto.TaskDTO;
import com.test.test.dto.ResponseDTO;
import com.test.test.service.TestService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final TestService testService;

    public Controller(TestService testService) {
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createTask(@RequestBody @Valid TaskDTO taskDto) {
        logger.info("Received request to create a new task");
        return ResponseEntity.ok(testService.createTask(taskDto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAllTasks() {
        logger.info("Received request to fetch all tasks");
        return ResponseEntity.ok(testService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTask(@PathVariable Long id) {
        logger.info("Received request to fetch task with ID: {}", id);
        return ResponseEntity.ok(testService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTask(
            @PathVariable Long id,
            @RequestBody @Valid TaskDTO taskDTO) {
        logger.info("Received request to update task with ID: {}", id);
        return ResponseEntity.ok(testService.updateTask(id, taskDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        logger.info("Received request to delete task with ID: {}", id);
        testService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}