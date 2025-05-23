package org.abramov.spring.testovoe.userservice.service;

import org.abramov.spring.testovoe.userservice.dto.request.CreateUserDto;
import org.abramov.spring.testovoe.userservice.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> getAllUsers(Integer pageNumber, Integer pageSize);

    User getUserByUserId(UUID userId);

    User getUserByUsername(String username);

    User updateUser(User user);

    User createUser(CreateUserDto createUserDto);

    void deleteUser(UUID userId);

    boolean existsByUsername(String username);
}
