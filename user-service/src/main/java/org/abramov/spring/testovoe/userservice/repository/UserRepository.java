package org.abramov.spring.testovoe.userservice.repository;

import org.abramov.spring.testovoe.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUsersByUsername(String username);
}
