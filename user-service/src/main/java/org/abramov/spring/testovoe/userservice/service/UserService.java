package org.abramov.spring.testovoe.userservice.service;

import org.abramov.spring.testovoe.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    // Получить всех пользователей
    List<User> getAllUsers();

    // Получить пользователя по идентификатору
    User getUserByUserId(UUID userId);

    // Получить пользователя по его логину
    User getUserByUserLogin(String userLogin);

    // Редактировать пользователя, при редактировании указываются свойства: логин, фамилия, имя
    User updateUser(String userLogin, String userLastName, String userFirstName);
}
