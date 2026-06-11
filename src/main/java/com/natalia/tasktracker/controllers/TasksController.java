package com.natalia.tasktracker.controllers;

import com.natalia.tasktracker.dto.TaskDto;
import com.natalia.tasktracker.mappers.TaskMapper;
import com.natalia.tasktracker.models.Task;
import com.natalia.tasktracker.services.TasksService;
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
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksService;
    private final TaskMapper taskMapper;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTask(@RequestBody @Valid TaskDto taskDto) {
        Task task = taskMapper.convertToEntity(taskDto);
        Task createdTask = tasksService.create(task);
        TaskDto createdTaskDto = taskMapper.convertToDto(createdTask);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Task created successfully");
        response.put("task", createdTaskDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTask(@PathVariable Long id) {
        Task task = tasksService.findById(id);
        TaskDto taskDto = taskMapper.convertToDto(task);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Task found successfully");
        response.put("task", taskDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTasks() {
        List<TaskDto> tasks = tasksService.findAll()
                .stream()
                .map(taskMapper::convertToDto)
                .collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Tasks retrieved successfully");
        response.put("tasks", tasks);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editTask(@RequestBody @Valid TaskDto taskDto, @PathVariable Long id) {
        Task task = tasksService.edit(id, taskMapper.convertToEntity(taskDto));
        TaskDto updatedTaskDto = taskMapper.convertToDto(task);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Task updated successfully");
        response.put("task", updatedTaskDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable Long id) {
        tasksService.delete(id);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Task deleted successfully");

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{taskId}/{projectId}/assignProject")
    public ResponseEntity<Map<String, Object>> assignProject(@PathVariable Long taskId, @PathVariable Long projectId) {
        tasksService.assignProject(taskId, projectId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Project assigned successfully");

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{taskId}/{userId}/assignUser")
    public ResponseEntity<Map<String, Object>> assignUser(@PathVariable Long taskId, @PathVariable Long userId) {
        tasksService.assignUser(taskId, userId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User assigned successfully");

        return ResponseEntity.ok(response);
    }
}
