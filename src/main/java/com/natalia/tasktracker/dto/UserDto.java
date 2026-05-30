package com.natalia.tasktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.natalia.tasktracker.models.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 255, message = "Name should be between 2 and 255 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255, message = "Password should be between 8 and 255 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private UserRole role = UserRole.USER;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<ProjectDto> projects = new ArrayList<>();

    private List<TaskDto> tasks = new ArrayList<>();

}
