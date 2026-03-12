package com.remi.production_issue_tracker.repository;

import com.remi.production_issue_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
