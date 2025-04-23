package org.abramov.spring.testovoe.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.dto.request.UpdateUserDto;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.abramov.spring.testovoe.userservice.mapper.UserMapper;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        final var users = userService.getAllUsers(pageNumber, pageSize);
        final var userDtoList = users.stream().map(user -> UserMapper.toUserDto(user)).toList();
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/userid/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable UUID userId) {
        final var user = userService.getUserByUserId(userId);
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        final var user = userService.getUserByUsername(username);
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId, @RequestBody UpdateUserDto updateUserDto) {
        final var user = userService.updateUser(UserMapper.toUser(userId, updateUserDto));
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        final var user = userService.createUser(createUserDto);

        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }

}

