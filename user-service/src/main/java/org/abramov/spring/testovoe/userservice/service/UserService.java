package org.abramov.spring.testovoe.userservice.service;
import org.abramov.spring.testovoe.userservice.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    //получить всех пользователей
    List<User> getAllUsers();

    //получить пользователя по идентификатору
    User getUserByUserId(UUID userId);

    //получить пользователя по его логину (username)
    User getUserByUsername(String username);

    //редактировать пользователя (осуществляется по его идентификатору): логин, фамилия, имя
    User updateUser(User user);



    /**
     *     private UUID userId;
     *     private String username;
     *     private String userLastName;
     *     private String userFirstName;
     */
}
