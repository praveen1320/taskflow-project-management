
package com.taskflow_backend.dto;

import java.time.LocalDateTime;

import com.taskflow_backend.dto.ProjectResponseDTO;

public class ProjectResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String status;
    private LocalDateTime createdAt;

    public ProjectResponseDTO(
            Long id,
            String name,
            String description,
            String status,
            LocalDateTime createdAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
