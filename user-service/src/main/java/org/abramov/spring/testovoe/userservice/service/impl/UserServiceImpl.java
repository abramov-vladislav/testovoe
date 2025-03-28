package org.abramov.spring.testovoe.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.abramov.spring.testovoe.userservice.exception.UserAlreadyExistsException;
import org.abramov.spring.testovoe.userservice.exception.UserNotFoundException;
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

    @Override
    public List<User> getAllUsers() throws UserNotFoundException {
        try {
            return userRepository.findAll();
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Пользователи не найдены");
        }
    }

    @Override
    public User getUserByUserId(UUID userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findUsersByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {

        User userExisting = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        userExisting.setUsername(user.getUsername());
        userExisting.setUserLastName(user.getUserLastName());
        userExisting.setUserFirstName(user.getUserFirstName());

        return userRepository.save(userExisting);
    }

    @Override
    public User createUser(CreateUserDto createUserDto) throws UserAlreadyExistsException {

        boolean usernameExists = userRepository.existsUserByUsername(createUserDto.getUsername());
        if (usernameExists) {
            throw new UserAlreadyExistsException(createUserDto.getUsername());
        }

        return userRepository.save(UserMapper.toUser(createUserDto));
    }

    @Override
    public void deleteUser(UUID userId) throws UserNotFoundException {

        try {
            userRepository.deleteById(userId);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Пользователь не найден");
        }
    }
}

