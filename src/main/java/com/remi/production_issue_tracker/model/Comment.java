package com.remi.production_issue_tracker.model;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "commented_by_id")
    private User commentedBy;
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public User getCommentedBy() { return commentedBy; }
    public void setCommentedBy(User commentedBy) { this.commentedBy = commentedBy; }

    public Issue getIssue() { return issue; }
    public void setIssue(Issue issue) { this.issue = issue; }
}
