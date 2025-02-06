package org.abramov.spring.testovoe.userservice.service.impl;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.model.User;
import org.abramov.spring.testovoe.userservice.repository.UserRepository;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    //ИСПРАВИТЬ НИЖЕ ВСЕ
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserId(UUID userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserByUserLogin(String userLogin) {
        return (User) userRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(String userLogin, String userLastName, String userFirstName) {
        // Здесь добавьте логику обновления и проверки уникальности логина
        // Например, получите пользователя по id, измените поля и сохраните в репозиторий.
        return null;
    }
}

/*
Необходимо реализовать логику взаимодействия с репозиторием, валидацию уникальности логина
и обработку исключительных ситуаций (например, пользователь не найден или новый логин уже занят).
При обновлении, если новый логин отличается от старого, нужно проверить его уникальность в базе.
 */
