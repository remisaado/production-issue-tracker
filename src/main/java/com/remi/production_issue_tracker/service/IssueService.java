package com.remi.production_issue_tracker.service;

import com.remi.production_issue_tracker.dto.CommentDTO;
import com.remi.production_issue_tracker.dto.IssueDTO;
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

    public List<IssueDTO> getAllIssues() {
        List<Issue> issues = issueRepository.findAll();

        return issues.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public IssueDTO createIssue(Issue issue) {
        Issue savedIssue = issueRepository.save(issue);
        return mapToDTO(savedIssue);
    }

    public IssueDTO addComment(Long issueId, Comment comment) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        comment.setIssue(issue);

        issue.getComments().add(comment);

        Issue savedIssue = issueRepository.save(issue);
        return mapToDTO(savedIssue);
    }

    public void removeComment(Long issueId, Long commentId) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        Comment commentToRemove = issue.getComments().stream()
                .filter(comment -> comment.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        issue.getComments().remove(commentToRemove);

        issueRepository.save(issue);
    }

    public IssueDTO getIssueDTOById(Long id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        return mapToDTO(issue);
    }

    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }

    public IssueDTO updateIssue(Long id, Issue updatedIssue) {
        Issue existingIssue = issueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        existingIssue.setTitle(updatedIssue.getTitle());
        existingIssue.setDescription(updatedIssue.getDescription());
        existingIssue.setStatus(updatedIssue.getStatus());
        existingIssue.setPriority(updatedIssue.getPriority());
        existingIssue.setReportedBy(updatedIssue.getReportedBy());
        existingIssue.setAssignedTo(updatedIssue.getAssignedTo());
        existingIssue.setProductionLine(updatedIssue.getProductionLine());

        Issue savedIssue = issueRepository.save(existingIssue);
        return mapToDTO(savedIssue);
    }

    private IssueDTO mapToDTO(Issue issue) {
        List<CommentDTO> commentDTOs = issue.getComments().stream()
                .map(comment -> new CommentDTO(
                        comment.getId(),
                        comment.getText(),
                        comment.getCommentedBy().getId(),
                        comment.getCommentedBy().getName()
                )).toList();

        return new IssueDTO(
                issue.getId(),
                issue.getTitle(),
                issue.getDescription(),
                issue.getStatus().name(),
                issue.getPriority().name(),
                issue.getCreatedAt(),
                issue.getReportedBy() != null ? issue.getReportedBy().getId() : null,
                issue.getReportedBy() != null ? issue.getReportedBy().getName() : null,
                issue.getAssignedTo() != null ? issue.getAssignedTo().getId() : null,
                issue.getAssignedTo() != null ? issue.getAssignedTo().getName() : null,
                issue.getProductionLine() != null ? issue.getProductionLine().getId() : null,
                issue.getProductionLine() != null ? issue.getProductionLine().getTitle() : null,
                commentDTOs
        );
    }
}
