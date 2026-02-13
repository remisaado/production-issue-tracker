package com.remi.production_issue_tracker.controller;

import com.remi.production_issue_tracker.model.Issue;
import com.remi.production_issue_tracker.service.IssueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) { this.issueService = issueService; }

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @PostMapping
    public Issue CreateIssue(@RequestBody Issue issue) {
        return issueService.createIssue(issue);
    }
}
