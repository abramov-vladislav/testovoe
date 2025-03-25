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
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не может быть найден"));
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User updateUser(User user) {

        return null;
    }

    /**
         * При редактировании запрос может быть отклонен,
         * если новый логин (username) не является старым и при этом он не уникален
         */
}

