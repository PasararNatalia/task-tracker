package com.natalia.tasktracker.mappers;

import com.natalia.tasktracker.dto.UserDto;
import com.natalia.tasktracker.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto convertToDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());

        return dto;
    }

    public User convertToEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return user;
    }
}
