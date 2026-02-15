package com.remi.production_issue_tracker.service;

import com.remi.production_issue_tracker.model.User;
import com.remi.production_issue_tracker.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User createUser(User user) { return userRepository.save(user); }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
