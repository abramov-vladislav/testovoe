package org.abramov.spring.testovoe.userservice.repository;
import org.abramov.spring.testovoe.userservice.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByUserId (UUID userId);
}
