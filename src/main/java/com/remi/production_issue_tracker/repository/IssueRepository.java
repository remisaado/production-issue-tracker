package com.remi.production_issue_tracker.repository;

import com.remi.production_issue_tracker.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
