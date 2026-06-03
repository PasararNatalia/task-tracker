package com.natalia.tasktracker.controllers;

import com.natalia.tasktracker.dto.ProjectDto;
import com.natalia.tasktracker.dto.UserDto;
import com.natalia.tasktracker.mappers.ProjectMapper;
import com.natalia.tasktracker.mappers.UserMapper;
import com.natalia.tasktracker.models.Project;
import com.natalia.tasktracker.services.ProjectsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectsService projectsService;
    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProject(@RequestBody @Valid ProjectDto projectDto) {
        Project project = projectMapper.convertToEntity(projectDto);
        Project createdProject = projectsService.create(project);
        ProjectDto createdProjectDto = projectMapper.convertToDto(createdProject);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Project created successfully");
        response.put("project", createdProjectDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProject(@PathVariable Long id) {
        Project project = projectsService.findById(id);
        ProjectDto projectDto = projectMapper.convertToDto(project);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Project found successfully");
        response.put("project", projectDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALlProjects() {
        List<ProjectDto> projects = projectsService.findAll()
                .stream()
                .map(projectMapper::convertToDto)
                .collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Projects retrieved successfully");
        response.put("projects", projects);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editProject(@RequestBody @Valid ProjectDto projectDto, @PathVariable Long id) {
        Project project = projectsService.edit(id, projectMapper.convertToEntity(projectDto));
        ProjectDto updatedProjectDto = projectMapper.convertToDto(project);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Project updated successfully");
        response.put("project", updatedProjectDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProject(@PathVariable Long id) {
        projectsService.delete(id);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Project deleted successfully");

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<Map<String, Object>> assignUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
       projectsService.assign(id, userMapper.convertToEntity(userDto));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User assigned successfully");

        return ResponseEntity.ok(response);
    }
}
