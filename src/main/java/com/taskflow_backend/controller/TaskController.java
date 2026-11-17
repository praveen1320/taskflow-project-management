
package com.taskflow_backend.controller;

import com.taskflow_backend.entity.Task;
import com.taskflow_backend.service.TaskService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.taskflow_backend.dto.TaskResponseDTO;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create task under a project
    @PostMapping("/project/{projectId}")
public TaskResponseDTO createTask(
        @PathVariable Long projectId,
        @Valid @RequestBody Task task) {

    return taskService.createTask(projectId, task);
}

    // Get all tasks
    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/project/{projectId}")
    public List<TaskResponseDTO> getTasksByProject(
            @PathVariable Long projectId) {

        return taskService.getTasksByProject(projectId);
    }

    // Delete task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(
            @PathVariable Long taskId) {

        taskService.deleteTask(taskId);

        return ResponseEntity.ok("Task deleted successfully!");
    }

    @PutMapping("/{taskId}")
public TaskResponseDTO updateTask(
        @PathVariable Long taskId,
        @Valid @RequestBody Task task) {

    return taskService.updateTask(taskId, task);
}
}
