package org.abramov.spring.testovoe.userservice.controller;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}

/*
UserController пока только с заготовкой, без методов.
Рекомендации:

Добавь методы для каждого REST эндпоинта (получение пользователей, получение по ID/логину, обновление).
Не забудь добавить документацию Swagger (например, с использованием springdoc-openapi или springfox).
 */