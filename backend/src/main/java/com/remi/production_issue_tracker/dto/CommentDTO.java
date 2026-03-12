package com.remi.production_issue_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentDTO {
    private Long id;
    @NotBlank(message = "Text cannot be empty")
    private String text;
    @NotNull(message = "Commenting user must be set")
    private Long commentedById;
    private String commentedByName;

    public CommentDTO(Long id, String text, Long commentedById, String commentedByName) {
        this.id = id;
        this.text = text;
        this.commentedById = commentedById;
        this.commentedByName = commentedByName;
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public Long getCommentedById() { return commentedById; }
    public String getCommentedByName() { return commentedByName; }
}
