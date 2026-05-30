package com.natalia.tasktracker.controllers;

import com.natalia.tasktracker.dto.ProjectDto;
import com.natalia.tasktracker.dto.UserDto;
import com.natalia.tasktracker.mappers.ProjectMapper;
import com.natalia.tasktracker.mappers.UserMapper;
import com.natalia.tasktracker.models.Project;
import com.natalia.tasktracker.services.ProjectsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectsService projectsService;
    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;

    @PostMapping
    public ProjectDto createProject(@RequestBody @Valid ProjectDto projectDto) {
        Project project = projectMapper.convertToEntity(projectDto);
        Project createdProject = projectsService.create(project);
        return projectMapper.convertToDto(createdProject);
    }

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable Long id) {
        Project project = projectsService.findById(id);
        return projectMapper.convertToDto(project);
    }

    @GetMapping
    public List<ProjectDto> getALlProjects() {
        return projectsService.findAll().stream().map(projectMapper::convertToDto).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public ProjectDto editProject(@RequestBody @Valid ProjectDto projectDto, @PathVariable Long id) {
        Project project = projectsService.edit(id, projectMapper.convertToEntity(projectDto));
        return projectMapper.convertToDto(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectsService.delete(id);
    }

    @PatchMapping("/{id}/assign")
    public void assignUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
       projectsService.assign(id, userMapper.convertToEntity(userDto));
    }
}
