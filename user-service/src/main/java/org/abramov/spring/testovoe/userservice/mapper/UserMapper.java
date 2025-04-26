package org.abramov.spring.testovoe.userservice.mapper;

import org.abramov.spring.testovoe.userservice.dto.UserCRUD;
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

    /**
     * Этот метод преобразует одну сущность User в объект UserCRUD,
     * который используется в сообщении Kafka (в виде записи о пользователе).
     *
     * Назначение: Преобразовать сущность User в более упрощенную структуру UserCRUD,
     * которая, возможно, не будет содержать всех полей из оригинальной сущности User,
     * а только те, которые важны для передачи через Kafka.
     *
     * Когда использовать: Используется, когда тебе нужно создать объект UserCRUD для одного пользователя,
     * например, чтобы отправить его в Kafka или выполнить валидацию/логику в сервисе.
     */
    static UserCRUD toUserCRUD(User user) {
        return new UserCRUD()
                .setId(user.getUserId())
                .setUsername(user.getUsername())
                .setUserFirstName(user.getUserFirstName())
                .setUserLastName(user.getUserLastName());
    }

}
