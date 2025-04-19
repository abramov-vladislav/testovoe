package org.abramov.spring.testovoe.userservice.repository;

import org.abramov.spring.testovoe.userservice.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {

    List<User> getAllUsers(Integer pageNumber, Integer pageSize);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByUserId(UUID userId);

    boolean existsUserByUsername(String username);

    User createUser(User userExisting);

    void deleteById(UUID userId);


}
