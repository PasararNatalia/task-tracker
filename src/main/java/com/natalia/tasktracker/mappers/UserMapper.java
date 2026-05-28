package com.natalia.tasktracker.mappers;

import com.natalia.tasktracker.dto.UserDto;
import com.natalia.tasktracker.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final ModelMapper mapper;

    public User convertToUser(UserDto dto) {
        return mapper.map(dto, User.class);
    }

    public UserDto convertToDto(User user) {
        return  mapper.map(user, UserDto.class);
    }
}
