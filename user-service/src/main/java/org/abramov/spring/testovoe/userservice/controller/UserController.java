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
