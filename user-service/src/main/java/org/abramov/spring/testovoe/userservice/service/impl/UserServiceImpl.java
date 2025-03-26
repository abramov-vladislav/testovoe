package org.abramov.spring.testovoe.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.abramov.spring.testovoe.userservice.repository.UserRepository;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserId(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не может быть найден"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не может быть найден"));
    }

    @Override
    public User updateUser(User user) {
        User userExisting = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не может быть найден"));

        userExisting.setUsername(user.getUsername());
        userExisting.setUserLastName(user.getUserLastName());
        userExisting.setUserFirstName(user.getUserFirstName());

        return userRepository.save(userExisting);
    }

    @Override
    public User createUser(User user) {
        if (user.getUserId() == null) {
            user.setUserId(UUID.randomUUID());
        }

        userRepository.save(user);
        return user;
    }


}

