package com.remi.production_issue_tracker.dto;

public class CommentDTO {
    private Long id;
    private String text;
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
