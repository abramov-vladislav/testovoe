package org.abramov.spring.testovoe.userservice.repository.impl;

import org.abramov.spring.testovoe.userservice.entity.User;
import org.abramov.spring.testovoe.userservice.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<User> findUsersByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return false;
    }

    @Override
    public List<User> getAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return Optional.empty();
    }
}
