
package com.taskflow_backend.service;

import com.taskflow_backend.entity.Task;
import com.taskflow_backend.entity.Project;
import com.taskflow_backend.repository.TaskRepository;
import com.taskflow_backend.repository.ProjectRepository;

import org.springframework.stereotype.Service;
import com.taskflow_backend.dto.TaskResponseDTO;

import com.taskflow_backend.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(
            TaskRepository taskRepository,
            ProjectRepository projectRepository) {

        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    // Create task under a project
  
public TaskResponseDTO createTask(Long projectId, Task task) {

    Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new ResourceNotFoundException(
        "Project not found with ID: " + projectId));

    task.setProject(project);

    Task savedTask = taskRepository.save(task);

    return convertToDTO(savedTask);
}


    // Get all tasks
   public List<TaskResponseDTO> getAllTasks() {

    return taskRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .toList();
}

    // Get tasks belonging to a particular project
    public List<TaskResponseDTO> getTasksByProject(Long projectId) {

    return taskRepository.findByProjectId(projectId)
            .stream()
            .map(this::convertToDTO)
            .toList();
}

    // Delete task
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }



public TaskResponseDTO updateTask(Long taskId, Task updatedTask) {

    Task existingTask = taskRepository.findById(taskId)
        .orElseThrow(() -> new ResourceNotFoundException(
                "Task not found with ID: " + taskId));

    existingTask.setTitle(updatedTask.getTitle());
    existingTask.setDescription(updatedTask.getDescription());
    existingTask.setStatus(updatedTask.getStatus());

    Task savedTask = taskRepository.save(existingTask);

    return convertToDTO(savedTask);
}




private TaskResponseDTO convertToDTO(Task task) {

    return new TaskResponseDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            task.getCreatedAt(),
            task.getProject().getId(),
            task.getProject().getName()
    );
}


}
