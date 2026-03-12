package com.remi.production_issue_tracker.repository;

import com.remi.production_issue_tracker.model.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    Page<Issue> findByStatus(Issue.IssueStatus status, Pageable pageable);

    Page<Issue> findByPriority(Issue.Priority priority, Pageable pageable);

    Page<Issue> findByStatusAndPriority(Issue.IssueStatus status, Issue.Priority priority, Pageable pageable);
}
