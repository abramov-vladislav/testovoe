package org.abramov.spring.testovoe.userservice.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.abramov.spring.testovoe.userservice.mapper.UserMapper;
import org.abramov.spring.testovoe.userservice.repository.UserRepository;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

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
        return userRepository.findUsersByUsername(username)
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
    public User createUser(CreateUserDto user) {
//        if (user.getUserId() == null) {
//            user.setUserId(UUID.randomUUID());
//        }

        userRepository.findUsersByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    throw new RuntimeException("Пользователь с таким username уже существует");
                });
//        User createdUser = new User();
//                createdUser.setUsername(user.getUsername());
//                createdUser.setUserFirstName(user.getUserFirstName());
//                createdUser.setUserLastName(user.getUserLastName());
        User createdUser = UserMapper.toUser(user);

        return userRepository.save(createdUser);
    }


}

