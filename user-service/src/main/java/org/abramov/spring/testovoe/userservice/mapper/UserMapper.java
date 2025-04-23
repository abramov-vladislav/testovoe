package org.abramov.spring.testovoe.userservice.mapper;

import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.dto.request.UpdateUserDto;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.abramov.spring.testovoe.userservice.entity.User;

import java.util.UUID;

public interface UserMapper {

    static User toUser(CreateUserDto createUserDto) {
        return new User()
                .setUserId(UUID.randomUUID())
                .setUsername(createUserDto.getUsername())
                .setUserLastName(createUserDto.getUserLastName())
                .setUserFirstName(createUserDto.getUserFirstName());
    }

    static User toUser(UUID userId, UpdateUserDto updateUserDto) {
        return new User()
                .setUserId(userId)
                .setUsername(updateUserDto.getUsername())
                .setUserLastName(updateUserDto.getUserLastName())
                .setUserFirstName(updateUserDto.getUserFirstName());
    }

    static UserDto toUserDto(User user) {
        return new UserDto(user.getUserId(), user.getUsername(), user.getUserLastName(), user.getUserFirstName());
    }

    static UserDto toUserEventDto(User user) {
        /**
         * надо реализовать
         */
    }
}
