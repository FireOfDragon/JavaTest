package com.test.test.dto;

import com.test.test.model.TaskStatus;

public class ResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;

    public ResponseDTO(Long id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // Геттеры
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
}
