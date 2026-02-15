package com.remi.production_issue_tracker.service;

import com.remi.production_issue_tracker.model.Comment;
import com.remi.production_issue_tracker.model.Issue;
import com.remi.production_issue_tracker.repository.IssueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public Issue addComment(Long issueId, Comment comment) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        comment.setIssue(issue);

        issue.getComments().add(comment);

        return issueRepository.save(issue);
    }

    public void removeComment(Long issueId, Long commentId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        Comment commentToRemove = issue.getComments().stream()
                .filter(comment -> comment.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        issue.getComments().remove(commentToRemove);

        issueRepository.save(issue);
    }

    public Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));
    }

    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }

    public Issue updateIssue(Long id, Issue updatedIssue) {
        Issue existingIssue = issueRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        existingIssue.setTitle(updatedIssue.getTitle());
        existingIssue.setDescription(updatedIssue.getDescription());
        existingIssue.setStatus(updatedIssue.getStatus());
        existingIssue.setPriority(updatedIssue.getPriority());
        existingIssue.setReportedBy(updatedIssue.getReportedBy());
        existingIssue.setAssignedTo(updatedIssue.getAssignedTo());
        existingIssue.setProductionLine(updatedIssue.getProductionLine());

        return issueRepository.save(existingIssue);
    }
}
