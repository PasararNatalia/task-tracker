package com.natalia.tasktracker.controllers;

import com.natalia.tasktracker.dto.UserDto;
import com.natalia.tasktracker.mappers.UserMapper;
import com.natalia.tasktracker.models.User;
import com.natalia.tasktracker.services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {

        User user = userMapper.convertToEntity(userDto);
        User createdUser = usersService.create(user);
        return userMapper.convertToDto(createdUser);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        User user = usersService.findById(id);
        return userMapper.convertToDto(user);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return usersService.findALl().stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public UserDto editUser(@RequestBody @Valid UserDto userDto, @PathVariable Long id) {
        User user = usersService.edit(id, userMapper.convertToEntity(userDto));
        return userMapper.convertToDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }


}

