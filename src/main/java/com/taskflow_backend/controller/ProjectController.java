
package com.taskflow_backend.controller;

import com.taskflow_backend.entity.Project;
import com.taskflow_backend.service.ProjectService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    // Get all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Get project by ID
    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // Delete project

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {

        boolean deleted = projectService.deleteProject(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Project deleted successfully!");
    }

    @PutMapping("/{id}")
    public Optional<Project> updateProject(
            @PathVariable Long id,
            @RequestBody Project project) {

        return projectService.updateProject(id, project);
    }

}
