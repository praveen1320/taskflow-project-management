
package com.taskflow_backend.service;

import com.taskflow_backend.entity.Project;
import com.taskflow_backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import com.taskflow_backend.dto.ProjectResponseDTO;

import com.taskflow_backend.exception.ResourceNotFoundException;

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
    public ProjectResponseDTO createProject(Project project) {
    Project savedProject = projectRepository.save(project);

    return convertToDTO(savedProject);
}

    // Get all projects
    public List<ProjectResponseDTO> getAllProjects() {
    return projectRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .toList();
}

    // Get project by ID
    public Optional<ProjectResponseDTO> getProjectById(Long id) {
    return projectRepository.findById(id)
            .map(this::convertToDTO);
}

    // Delete project

    public boolean deleteProject(Long id) {

        if (!projectRepository.existsById(id)) {
            return false;
        }

        projectRepository.deleteById(id);
        return true;
    }

   public ProjectResponseDTO updateProject(Long id, Project updatedProject) {

    Project existingProject = projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Project not found with ID: " + id));

    existingProject.setName(updatedProject.getName());
    existingProject.setDescription(updatedProject.getDescription());
    existingProject.setStatus(updatedProject.getStatus());

    Project savedProject = projectRepository.save(existingProject);

    return convertToDTO(savedProject);
}


private ProjectResponseDTO convertToDTO(Project project) {

    return new ProjectResponseDTO(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getStatus(),
            project.getCreatedAt()
    );
}


}
