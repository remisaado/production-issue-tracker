package com.remi.production_issue_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    @NotBlank(message = "Title cannot be empty")
    private String title;
    private String description;
    @NotNull(message = "Status must be set")
    @Enumerated(EnumType.STRING)
    private IssueStatus status = IssueStatus.OPEN;
    @NotNull(message = "Priority must be set")
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;
    @NotNull(message = "Reporting user must be set")
    @ManyToOne
    @JoinColumn(name = "reported_by_id")
    private User reportedBy;
    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;
    @NotNull(message = "Production line must be set")
    @ManyToOne
    @JoinColumn(name = "production_line_id")
    private ProductionLine productionLine;
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public enum IssueStatus  {
        OPEN,
        IN_PROGRESS,
        RESOLVED,
        CLOSED
    }

    public enum Priority  {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }

    public Issue() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public IssueStatus getStatus() { return status; }
    public void setStatus(IssueStatus status) { this.status = status; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public User getReportedBy() { return reportedBy; }
    public void setReportedBy(User reportedBy) { this.reportedBy = reportedBy; }

    public User getAssignedTo() { return assignedTo; }
    public void setAssignedTo(User assignedTo) { this.assignedTo = assignedTo; }

    public ProductionLine getProductionLine() { return productionLine; }
    public void setProductionLine(ProductionLine productionLine) { this.productionLine = productionLine; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }
}
