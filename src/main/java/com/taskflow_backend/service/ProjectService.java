
package com.taskflow_backend.service;

import com.taskflow_backend.entity.Project;
import com.taskflow_backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    // Constructor injection
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Create project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get project by ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    // Delete project

    public boolean deleteProject(Long id) {

        if (!projectRepository.existsById(id)) {
            return false;
        }

        projectRepository.deleteById(id);
        return true;
    }

    public Optional<Project> updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id).map(existingProject -> {
            existingProject.setName(updatedProject.getName());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setStatus(updatedProject.getStatus());
            return projectRepository.save(existingProject);
        });
    }

}
