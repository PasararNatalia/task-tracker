package com.natalia.tasktracker.services;

import com.natalia.tasktracker.models.Project;
import com.natalia.tasktracker.models.User;
import com.natalia.tasktracker.repositories.ProjectsRepository;
import com.natalia.tasktracker.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public Project create(Project project) {
        return projectsRepository.save(project);
    }

    public Project findById(Long id) {
        return projectsRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public List<Project> findAll() {
        return projectsRepository.findAll();
    }

    @Transactional
    public Project edit(Long id, Project project) {
        Project projectToEdit = findById(id);
        projectToEdit.setName(project.getName());
        projectToEdit.setDescription(project.getDescription());

        //todo: only for admin
        projectToEdit.setOwner(project.getOwner());

        return projectsRepository.save(projectToEdit);
    }

    @Transactional
    public void delete(Long id) {
        projectsRepository.deleteById(id);
    }

    @Transactional
    public void assign(Long projectId, User selectedUser) {
        Project project = findById(projectId);

        User user = usersRepository.findById(selectedUser.getId())
                .orElseThrow(() -> new RuntimeException(("User not found")));

        project.setOwner(user);
    }
}
