package com.natalia.tasktracker.mappers;

import com.natalia.tasktracker.dto.TaskDto;
import com.natalia.tasktracker.models.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto convertToDto(Task task) {
        if (task == null) {
            return null;
        }

        TaskDto dto = new TaskDto();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        dto.setDueDate(task.getDueDate());

        if (task.getProject() != null) {
            dto.setProjectId(task.getProject().getId());
        }

        if (task.getAssignee() != null) {
            dto.setAssigneeId(task.getAssignee().getId());
        }

        return dto;
    }

    public Task convertToEntity(TaskDto dto) {
        if (dto == null) {
            return null;
        }

        Task task = new Task();

        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());

        return task;
    }
}
