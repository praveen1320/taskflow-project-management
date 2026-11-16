
package com.taskflow_backend.dto;

import java.time.LocalDateTime;

public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private Long projectId;
    private String projectName;

    public TaskResponseDTO(
            Long id,
            String title,
            String description,
            String status,
            LocalDateTime createdAt,
            Long projectId,
            String projectName) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
}
