package com.remi.production_issue_tracker.model;

import jakarta.persistence.*;

@Entity
public class ProductionLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
