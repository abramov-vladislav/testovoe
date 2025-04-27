package org.abramov.spring.testovoe.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.userservice.client.kafka.producer.CRUDProducerUser;
import org.abramov.spring.testovoe.userservice.dto.UserCRUD;
import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.abramov.spring.testovoe.userservice.enums.EventTypeUser;
import org.abramov.spring.testovoe.userservice.exception.UserAlreadyExistsException;
import org.abramov.spring.testovoe.userservice.exception.UserNotFoundException;
import org.abramov.spring.testovoe.userservice.mapper.UserMapper;
import org.abramov.spring.testovoe.userservice.repository.UserRepository;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CRUDProducerUser crudProducerUser;

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

        userRepository.createUser(userExisting);

        UserCRUD userCRUD = UserMapper.toUserCRUD(userExisting).setEventType(EventTypeUser.USER_UPDATE);
        crudProducerUser.send(Collections.singletonList(userCRUD), EventTypeUser.USER_UPDATE);

        return userExisting;
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        log.info("Создание нового пользователя: {}", createUserDto);

        if (userRepository.existsUserByUsername(createUserDto.getUsername())) {
            throw new UserAlreadyExistsException(createUserDto.getUsername());
        }

        User user = userRepository.createUser(UserMapper.toUser(createUserDto));

        return user;
    }

    @Override
    public void deleteUser(UUID userId) throws UserNotFoundException {
        log.warn("Удаление пользователя с ID: {}", userId);

        User user = userRepository.getUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        try {
            userRepository.deleteById(userId);

            UserCRUD userCRUD = UserMapper.toUserCRUD(user).setEventType(EventTypeUser.USER_DELETE);
            crudProducerUser.send(Collections.singletonList(userCRUD), EventTypeUser.USER_DELETE);
        } catch (UserNotFoundException e) {
            log.error("Ошибка при удалении пользователя: {}", userId, e);
            throw new UserNotFoundException("Пользователь не найден");
        }
    }
}

