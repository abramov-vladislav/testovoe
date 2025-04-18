package org.abramov.spring.testovoe.userservice.repository;

import org.abramov.spring.testovoe.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    Optional<User> findUsersByUsername(String username);

    boolean existsUserByUsername(String username);

    Page<User> getAllUsers(Pageable pageable);

    Optional<User> findById(UUID userId);
}
