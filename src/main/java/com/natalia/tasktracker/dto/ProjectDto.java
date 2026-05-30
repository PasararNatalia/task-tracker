package com.natalia.tasktracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 255, message = "Name should be between 2 and 255 characters")
    private String name;

    @Size(max = 5000, message = "Description should not exceed 5000 characters")
    private String description;

    private Long ownerId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<TaskDto> tasks = new ArrayList<>();

}