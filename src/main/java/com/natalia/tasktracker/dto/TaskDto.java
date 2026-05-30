package com.natalia.tasktracker.dto;

import com.natalia.tasktracker.models.TaskPriority;
import com.natalia.tasktracker.models.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 255, message = "Title should be between 2 and 255 characters")
    private String title;

    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    private Long assigneeId;

    private Long projectId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDate dueDate;

}
