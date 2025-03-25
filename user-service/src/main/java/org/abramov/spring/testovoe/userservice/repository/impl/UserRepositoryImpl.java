package org.abramov.spring.testovoe.userservice.repository.impl;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.abramov.spring.testovoe.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<User> findByUserId(UUID userId) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
