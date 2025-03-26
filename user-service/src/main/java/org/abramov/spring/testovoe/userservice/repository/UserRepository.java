package org.abramov.spring.testovoe.userservice.repository;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    Optional<User> findByUserId (UUID userId);
    List<UserDto> findAll();
}
