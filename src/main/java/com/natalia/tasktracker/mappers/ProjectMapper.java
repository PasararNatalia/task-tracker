package com.natalia.tasktracker.mappers;

import com.natalia.tasktracker.dto.ProjectDto;
import com.natalia.tasktracker.models.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectDto convertToDto(Project project) {
        if (project == null) {
            return null;
        }

        ProjectDto dto = new ProjectDto();

        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setCreatedAt(project.getCreatedAt());
        dto.setUpdatedAt(project.getUpdatedAt());

        if (project.getOwner() != null) {
            dto.setOwnerId(project.getOwner().getId());
        }

        return dto;
    }

    public Project convertToEntity(ProjectDto dto) {
        if (dto == null) {
            return null;
        }

        Project project = new Project();

        project.setId(dto.getId());
        project.setName(dto.getName());

        project.setDescription(dto.getDescription());

        return project;

    }
}
