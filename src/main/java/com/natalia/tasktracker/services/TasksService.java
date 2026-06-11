package com.natalia.tasktracker.services;

import com.natalia.tasktracker.models.Project;
import com.natalia.tasktracker.models.Task;
import com.natalia.tasktracker.models.User;
import com.natalia.tasktracker.repositories.ProjectsRepository;
import com.natalia.tasktracker.repositories.TasksRepository;
import com.natalia.tasktracker.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TasksService {

    private final TasksRepository tasksRepository;
    private final ProjectsRepository projectsRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public Task create(Task task) {
        return tasksRepository.save(task);
    }

    public Task findById(Long id) {
        return tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> findAll() {
        return tasksRepository.findAll();
    }

    @Transactional
    public Task edit(Long id, Task task) {
        Task taskToEdit = findById(id);

        taskToEdit.setTitle(task.getTitle());
        taskToEdit.setDescription(task.getDescription());

        return tasksRepository.save(taskToEdit);
    }

    @Transactional
    public void delete(Long id) {
        tasksRepository.deleteById(id);
    }

    @Transactional
    public void assignProject(Long taskId, Long projectId) {
        Task task = findById(taskId);

        Project project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException(("Project not found")));

        task.setProject(project);
    }

    @Transactional
    public void assignUser(Long taskId, Long userId) {
        Task task = findById(taskId);

        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(("User not found")));

        task.setAssignee(user);
    }
}
