package com.remi.production_issue_tracker.controller;

import com.remi.production_issue_tracker.dto.CommentDTO;
import com.remi.production_issue_tracker.dto.IssueDTO;
import com.remi.production_issue_tracker.model.Issue;
import com.remi.production_issue_tracker.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) { this.issueService = issueService; }

    @GetMapping
    public List<IssueDTO> getIssues(
            @RequestParam(required = false) Issue.IssueStatus status,
            @RequestParam(required = false) Issue.Priority priority)
    { return issueService.getIssues(status, priority); }

    @GetMapping("/{id}")
    public IssueDTO getIssueById(@PathVariable Long id) { return issueService.getIssueDTOById(id); }

    @PostMapping
    public IssueDTO createIssue(@Valid @RequestBody Issue issue) { return issueService.createIssue(issue); }

    @PutMapping("/{id}")
    public IssueDTO updateIssue(@PathVariable Long id, @Valid @RequestBody Issue issue)
    { return issueService.updateIssue(id, issue); }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable Long id) { issueService.deleteIssue(id); }

    @PostMapping("/{id}/comments")
    public IssueDTO addComment(@PathVariable Long id, @Valid @RequestBody CommentDTO commentDTO)
    { return issueService.addComment(id, commentDTO); }

    @DeleteMapping("/{issueId}/comments/{commentId}")
    public IssueDTO removeComment(@PathVariable Long issueId, @PathVariable Long commentId) {
        issueService.removeComment(issueId, commentId);
        return issueService.getIssueDTOById(issueId);
    }
}
