
package com.taskflow_backend.controller;

import com.taskflow_backend.dto.ProjectResponseDTO;
import com.taskflow_backend.entity.Project;
import com.taskflow_backend.service.ProjectService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")

public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Create project
    @PostMapping
public ProjectResponseDTO createProject(@Valid @RequestBody Project project) {
    return projectService.createProject(project);
}

    // Get all projects
    @GetMapping
public List<ProjectResponseDTO> getAllProjects() {
    return projectService.getAllProjects();
}

    // Get project by ID
    @GetMapping("/{id}")
public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id) {

    return projectService.getProjectById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

    // Delete project

   @DeleteMapping("/{id}")
public ResponseEntity<String> deleteProject(@PathVariable Long id) {

    boolean deleted = projectService.deleteProject(id);

    if (deleted) {
        return ResponseEntity.ok("Project deleted successfully");
    }

    return ResponseEntity.notFound().build();
}

   @PutMapping("/{id}")
public ResponseEntity<ProjectResponseDTO> updateProject(
        @PathVariable Long id,
        @Valid @RequestBody Project updatedProject) {

    ProjectResponseDTO updatedResponse =
            projectService.updateProject(id, updatedProject);

    return ResponseEntity.ok(updatedResponse);
}

}
