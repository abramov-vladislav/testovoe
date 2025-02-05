package org.abramov.spring.testovoe.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.model.User;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping ("/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable UUID userId) {
        final var user = userService.getUserByUserId(userId);
        return ResponseEntity.ok(user);
    }
}
