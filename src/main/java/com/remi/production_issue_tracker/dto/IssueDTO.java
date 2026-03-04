package com.remi.production_issue_tracker.dto;

import java.time.LocalDateTime;
import java.util.List;

public class IssueDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime createdAt;
    private Long reportedById;
    private String reportedByName;
    private Long assignedToId;
    private String assignedToName;
    private Long productionLineId;
    private String productionLineName;
    private List<CommentDTO> comments;

    public IssueDTO(
            Long id,
            String title,
            String description,
            String status,
            String priority,
            LocalDateTime createdAt,
            Long reportedById,
            String reportedByName,
            Long assignedToId,
            String assignedToName,
            Long productionLineId,
            String productionLineName,
            List<CommentDTO> comments
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdAt = createdAt;
        this.reportedById = reportedById;
        this.reportedByName = reportedByName;
        this.assignedToId = assignedToId;
        this.assignedToName = assignedToName;
        this.productionLineId = productionLineId;
        this.productionLineName = productionLineName;
        this.comments = comments;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getReportedById() { return reportedById; }
    public String getReportedByName() { return reportedByName; }
    public Long getAssignedToId() { return assignedToId; }
    public String getAssignedToName() { return assignedToName; }
    public Long getProductionLineId() { return productionLineId; }
    public String getProductionLineName() { return productionLineName; }
    public List<CommentDTO> getComments() { return comments; }
}
