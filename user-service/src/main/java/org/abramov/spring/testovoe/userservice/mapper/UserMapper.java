package org.abramov.spring.testovoe.userservice.mapper;

import io.micrometer.observation.annotation.Observed;
import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.dto.request.UpdateUserDto;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Mapper
@Observed
@Component
public interface UserMapper {

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
