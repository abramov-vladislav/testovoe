package org.abramov.spring.testovoe.userservice.controller.mapper;

import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.dto.request.UpdateUserDto;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.abramov.spring.testovoe.userservice.entity.User;

import java.util.UUID;

public class UserMapper {
    public static User toUser(CreateUserDto createUserDto) {
        return new User(createUserDto.getUserId(), createUserDto.getUsername(), createUserDto.getUserLastName(),
                createUserDto.getUserFirstName());
    }

    public static User toUser(UUID userId, UpdateUserDto updateUserDto) {
        return new User(userId, updateUserDto.getUsername(), updateUserDto.getUserLastName(),
                updateUserDto.getUserFirstName());
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getUserId(), user.getUsername(), user.getUserLastName(), user.getUserFirstName());
    }
}
