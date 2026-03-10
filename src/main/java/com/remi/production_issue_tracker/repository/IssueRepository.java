package com.remi.production_issue_tracker.repository;

import com.remi.production_issue_tracker.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByStatus(Issue.IssueStatus status);

    List<Issue> findByPriority(Issue.Priority priority);

    List<Issue> findByStatusAndPriority(Issue.IssueStatus status, Issue.Priority priority);
}
