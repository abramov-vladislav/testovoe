package org.abramov.spring.testovoe.userservice.service;

import org.abramov.spring.testovoe.userservice.model.User;
import java.util.UUID;

public interface UserService {
    User getUserByUserId(UUID userId);

}
