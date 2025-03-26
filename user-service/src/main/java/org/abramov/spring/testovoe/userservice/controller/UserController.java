package org.abramov.spring.testovoe.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.dto.request.UpdateUserDto;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.abramov.spring.testovoe.userservice.mapper.UserMapper;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //получить всех пользователей
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        final var users = userService.getAllUsers();
        final var userDtoList = users.stream().map(user -> UserMapper.toUserDto(user)).toList();
        return ResponseEntity.ok(userDtoList);
    }

    //получить пользователя по идентификатору
    @GetMapping("/{userId}")
    public User getUserByUserId(@PathVariable UUID userId) {
        return userService.getUserByUserId(userId);
    }

    //получить пользователя по его логину (username)
    @GetMapping("/{username}")
    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    //редактировать пользователя (осуществляется по его идентификатору): логин, фамилия, имя
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId, @RequestBody UpdateUserDto updateUserDto) {
        final var user = userService.updateUser(UserMapper.toUser(userId, updateUserDto));
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

}
