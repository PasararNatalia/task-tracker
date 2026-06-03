package com.natalia.tasktracker.controllers;

import com.natalia.tasktracker.dto.UserDto;
import com.natalia.tasktracker.mappers.UserMapper;
import com.natalia.tasktracker.models.User;
import com.natalia.tasktracker.services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        User createdUser = usersService.create(user);
        UserDto createdUserDto = userMapper.convertToDto(createdUser);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User created successfully");
        response.put("user", createdUserDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable Long id) {
        User user = usersService.findById(id);
        UserDto userDto = userMapper.convertToDto(user);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User found successfully");
        response.put("user", userDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<UserDto> users = usersService.findALl()
                .stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Users retrieved successfully");
        response.put("users", users);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editUser(
            @RequestBody @Valid UserDto userDto,
            @PathVariable Long id
    ) {
        User user = usersService.edit(id, userMapper.convertToEntity(userDto));
        UserDto updatedUserDto = userMapper.convertToDto(user);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User updated successfully");
        response.put("user", updatedUserDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User deleted successfully");

        return ResponseEntity.ok(response);
    }
}

