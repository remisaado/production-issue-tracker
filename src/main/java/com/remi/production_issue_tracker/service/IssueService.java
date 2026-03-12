package com.remi.production_issue_tracker.service;

import com.remi.production_issue_tracker.dto.CommentDTO;
import com.remi.production_issue_tracker.dto.IssueDTO;
import com.remi.production_issue_tracker.model.Comment;
import com.remi.production_issue_tracker.model.Issue;
import com.remi.production_issue_tracker.repository.IssueRepository;
import com.remi.production_issue_tracker.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    public IssueService(IssueRepository issueRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
    }

    public Page<IssueDTO> getIssues(Issue.IssueStatus status, Issue.Priority priority, Pageable pageable) {
        Page<Issue> issues;

        if (status != null && priority != null) {
            issues = issueRepository.findByStatusAndPriority(status, priority, pageable);
        } else if (status != null) {
            issues = issueRepository.findByStatus(status, pageable);
        } else if (priority != null) {
            issues = issueRepository.findByPriority(priority, pageable);
        } else {
            issues = issueRepository.findAll(pageable);
        }

        return issues.map(this::mapToDTO);
    }

    public IssueDTO createIssue(Issue issue) {
        Issue savedIssue = issueRepository.save(issue);
        return mapToDTO(savedIssue);
    }

    public IssueDTO addComment(Long issueId, CommentDTO commentDTO) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setCommentedBy(userRepository.findById(commentDTO.getCommentedById())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));
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

        if (updatedIssue.getTitle() != null) existingIssue.setTitle(updatedIssue.getTitle());
        if (updatedIssue.getDescription() != null) existingIssue.setDescription(updatedIssue.getDescription());
        if (updatedIssue.getStatus() != null) existingIssue.setStatus(updatedIssue.getStatus());
        if (updatedIssue.getPriority() != null) existingIssue.setPriority(updatedIssue.getPriority());
        if (updatedIssue.getReportedBy() != null) existingIssue.setReportedBy(updatedIssue.getReportedBy());
        if (updatedIssue.getAssignedTo() != null) existingIssue.setAssignedTo(updatedIssue.getAssignedTo());
        if (updatedIssue.getProductionLine() != null) existingIssue.setProductionLine(updatedIssue.getProductionLine());

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
