package org.abramov.spring.testovoe.userservice.repository.impl;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.model.User;
import org.abramov.spring.testovoe.userservice.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserId(UUID userId) {
        return Optional.empty();
    }
}
