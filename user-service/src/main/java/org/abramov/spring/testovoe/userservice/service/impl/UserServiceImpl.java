package org.abramov.spring.testovoe.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.abramov.spring.testovoe.userservice.exception.UserNotFoundException;
import org.abramov.spring.testovoe.userservice.mapper.UserMapper;
import org.abramov.spring.testovoe.userservice.repository.UserRepository;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final KafkaUserProducerService kafkaUserProducerService;

    @Override
    public List<User> getAllUsers(Integer pageNumber, Integer pageSize) {
        log.info("Получение всех пользователей: страница={}, размер={}", pageNumber, pageSize);

        return userRepository.getAllUsers(pageNumber, pageSize);
    }

    @Override
    public User getUserByUserId(UUID userId) throws UserNotFoundException {
        log.debug("Запрос пользователя по userId: {}", userId);

        return userRepository.getUserByUserId(userId)
                .orElseThrow(() -> {
                    log.warn("Пользователь с ID {} не найден", userId);
                    return new UserNotFoundException("Пользователь не найден");
                });
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        log.debug("Запрос пользователя по username: {}", username);

        return userRepository.getUserByUsername(username)
                .orElseThrow(() -> {
                    log.warn("Пользователь с username '{}' не найден", username);
                    return new UserNotFoundException("Пользователь не найден");
                });
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        log.info("Обновление пользователя: {}", user);
        User userExisting = userRepository.getUserByUserId(user.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        if (!userExisting.getUsername().equals(user.getUsername())
                && userRepository.existsUserByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username уже занят");
        }

        userExisting.setUsername(user.getUsername());
        userExisting.setUserLastName(user.getUserLastName());
        userExisting.setUserFirstName(user.getUserFirstName());

        return userRepository.createUser(userExisting);
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        log.info("Создание нового пользователя: {}", createUserDto);
        User user = userRepository.createUser(UserMapper.toUser(createUserDto));

        // Отправка события в Kafka
//        kafkaUserProducerService.send(createUserDto.getUsername());

        return user;
    }

    @Override
    public void deleteUser(UUID userId) throws UserNotFoundException {
        log.warn("Удаление пользователя с ID: {}", userId);

        try {
            userRepository.deleteById(userId);
        } catch (UserNotFoundException e) {
            log.error("Ошибка при удалении пользователя: {}", userId, e);
            throw new UserNotFoundException("Пользователь не найден");
        }
    }
}

