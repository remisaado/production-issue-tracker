package com.remi.production_issue_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotNull(message = "Role must be set")
    @Enumerated(EnumType.STRING)
    private Role role = Role.OPERATOR;

    public enum Role  {
        OPERATOR,
        TEAM_LEADER,
        TECHNICIAN,
        MANAGER
    }

    public long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
