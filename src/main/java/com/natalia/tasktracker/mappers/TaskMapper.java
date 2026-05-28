package com.natalia.tasktracker.mappers;

import com.natalia.tasktracker.dto.TaskDto;
import com.natalia.tasktracker.models.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskMapper {

    private final ModelMapper mapper;

    public Task convertToTask(TaskDto taskDto) {
        return mapper.map(taskDto, Task.class);
    }

    public TaskDto convertToDto(Task task) {
        return mapper.map(task, TaskDto.class);
    }

}
