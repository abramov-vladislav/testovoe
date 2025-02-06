package org.abramov.spring.testovoe.userservice.controller;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.model.User;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Получить всех пользователей: GET http://localhost:8080/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Получить пользователя по id: GET http://localhost:8080/users/1
    @GetMapping("/{id}")
    public User getUserByUserId(@PathVariable Long id) {
        return userService.getUserByUserId(id);
    }

    // Создать пользователя: POST http://localhost:8080/users
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}

/*
UserController пока только с заготовкой, без методов.
Рекомендации:

Добавь методы для каждого REST эндпоинта (получение пользователей, получение по ID/логину, обновление).
Не забудь добавить документацию Swagger (например, с использованием springdoc-openapi или springfox).
 */