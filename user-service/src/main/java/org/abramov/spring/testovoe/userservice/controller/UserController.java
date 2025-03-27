package org.abramov.spring.testovoe.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.dto.request.UpdateUserDto;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.abramov.spring.testovoe.userservice.mapper.UserMapper;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //получить всех пользователей
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        final var users = userService.getAllUsers();
        final var userDtoList = users.stream().map(user -> UserMapper.toUserDto(user)).toList();
        return ResponseEntity.ok(userDtoList);
    }

    //получить пользователя по идентификатору
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable UUID userId) {
        final var user = userService.getUserByUserId(userId);
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    //получить пользователя по его логину (username)
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        final var user = userService.getUserByUsername(username);
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    //редактировать пользователя (осуществляется по его идентификатору): логин, фамилия, имя
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId, @RequestBody UpdateUserDto updateUserDto) {
        final var user = userService.updateUser(UserMapper.toUser(userId, updateUserDto));
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    //создать пользователя
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        final var user = userService.createUser(createUserDto);
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    //удалить пользователя
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }

}
