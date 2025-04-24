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

//    /**
//     * Этот метод преобразует список пользователей (List<User>) в объект CRUDMessageUser,
//     * который, как правило, представляет собой сообщение, отправляемое в Kafka.
//     * Здесь ты, помимо конвертации в UserCRUD, также добавляешь информацию о типе события
//     * (например, создание, удаление или обновление пользователя), что делает его важным для того,
//     * чтобы Kafka-система знала, какого типа событие происходит.
//     *
//     * Назначение: Преобразует список сущностей User в сообщение Kafka (CRUDMessageUser),
//     * где указывается как сам список пользователей, так и тип события (EventTypeUser).
//     * Когда использовать: Используется, когда тебе нужно отправить несколько пользователей
//     * или событие, например, при массовом обновлении/удалении пользователей, или когда ты отправляешь
//     * событие с дополнительной информацией, такой как тип события.
//     */
//    static CRUDMessageUser toCRUDMessageUser(List<User> users, EventTypeUser type) {
//        List<UserCRUD> userCRUDList = users.stream()
//                .map(UserMapper::toUserCRUD)
//                .toList();
//
//        return new CRUDMessageUser()
//                .setUsers(userCRUDList)
//                .setEventType(type);
//    }

}
