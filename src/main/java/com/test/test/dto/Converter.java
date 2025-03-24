package com.test.test.dto;

import com.test.test.dto.TaskDTO;
import com.test.test.dto.ResponseDTO;
import com.test.test.model.Task;

public class Converter {
    public static Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        return task;
    }

    public static ResponseDTO toDto(Task task) {
        return new ResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStatus());
    }
}
