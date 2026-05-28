package com.natalia.tasktracker.controllers;

import com.natalia.tasktracker.dto.TaskDto;
import com.natalia.tasktracker.dto.UserDto;
import com.natalia.tasktracker.mappers.TaskMapper;
import com.natalia.tasktracker.mappers.UserMapper;
import com.natalia.tasktracker.models.Task;
import com.natalia.tasktracker.services.TasksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksService;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;

    @PostMapping
    public TaskDto createTask(@RequestBody @Valid TaskDto taskDto) {
        Task task = taskMapper.convertToTask(taskDto);
        Task createdTask = tasksService.create(task);
        return taskMapper.convertToDto(createdTask);
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable Long id) {
        Task task = tasksService.findById(id);
        return taskMapper.convertToDto(task);
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return tasksService.findAll().stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public TaskDto editTask(@RequestBody @Valid TaskDto taskDto, @PathVariable Long id) {
        Task task = tasksService.edit(id, taskMapper.convertToTask(taskDto));
        return taskMapper.convertToDto(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasksService.delete(id);
    }

    @PatchMapping("/{id}/assign")
    public void assignUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
        tasksService.assign(id, userMapper.convertToUser(userDto));
    }
}
