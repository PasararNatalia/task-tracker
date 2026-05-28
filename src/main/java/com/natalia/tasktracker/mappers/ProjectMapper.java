package com.natalia.tasktracker.mappers;

import com.natalia.tasktracker.dto.ProjectDto;
import com.natalia.tasktracker.models.Project;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectMapper {

    private final ModelMapper mapper;

    public Project convertToProject(ProjectDto projectDto) {
        return mapper.map(projectDto, Project.class);
    }

    public ProjectDto convertToDto(Project project) {
        return mapper.map(project, ProjectDto.class);
    }
}
