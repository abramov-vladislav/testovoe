package org.abramov.spring.testovoe.userservice.repository;

import org.abramov.spring.testovoe.userservice.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    Optional<User> findUsersByUsername(String username);

    boolean existsUserByUsername(String username);

    Optional<User> findById(UUID userId);

    User save(User userExisting);

    void deleteById(UUID userId);

    List<User> getAllUsers(Integer pageNumber, Integer pageSize);
}
