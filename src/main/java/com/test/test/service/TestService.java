package com.test.test.service;

import com.test.test.model.Task;
import com.test.test.repository.Rep;
import org.springframework.stereotype.Service;
import com.test.test.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);
    private final Rep Rep;

    public TestService(Rep taskRepository) {
        this.Rep = taskRepository;
    }

    public ResponseDTO createTask(TaskDTO taskDto) {
        logger.info("Creating a new task with title: {}", taskDto.getTitle());
        Task task = Converter.toEntity(taskDto);
        Task savedTask = Rep.save(task);
        return Converter.toDto(savedTask);
    }

    public List<ResponseDTO> getAllTasks() {
        logger.info("Fetching all tasks");
        return Rep.findAll()
                .stream()
                .map(Converter::toDto)
                .collect(Collectors.toList());
    }

    public ResponseDTO getTaskById(Long id) {
        logger.info("Fetching task with ID: {}", id);
        Task task = Rep.findById(id)
                .orElseThrow(() -> {
                    logger.error("Task not found with ID: {}", id);
                    return new RuntimeException("Task not found");
                });
        return Converter.toDto(task);
    }

    public ResponseDTO updateTask(Long id, TaskDTO taskDto) {
        logger.info("Updating task with ID: {}", id);
        Task task = Rep.findById(id)
                .orElseThrow(() -> {
                    logger.error("Task not found with ID: {}", id);
                    return new RuntimeException("Task not found");
                });

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());

        Task updatedTask = Rep.save(task);
        return Converter.toDto(updatedTask);
    }

    public void deleteTask(Long id) {
        logger.info("Deleting task with ID: {}", id);
        if (!Rep.existsById(id)) {
            logger.error("Task not found with ID: {}", id);
            throw new RuntimeException("Task not found");
        }
        Rep.deleteById(id);
    }
}