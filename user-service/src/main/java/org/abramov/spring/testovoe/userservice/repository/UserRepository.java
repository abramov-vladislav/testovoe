package org.abramov.spring.testovoe.userservice.repository;
import org.abramov.spring.testovoe.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUserId (UUID userId);
}
