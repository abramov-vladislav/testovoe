package org.abramov.spring.testovoe.userservice.client.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.userservice.client.kafka.producer.CRUDProducerUser;
import org.abramov.spring.testovoe.userservice.dto.UserCRUD;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CRUDMessageServiceUser {

    private final UserService userService;
    private final CRUDProducerUser crudProducerUser;

    private UserCRUD createUserCRUD(UUID id, String username, String userFirstName, String userLastName) {
        return new UserCRUD()
                .setId(id)
                .setUsername(username)
                .setUserFirstName(userFirstName)
                .setUserLastName(userLastName);
    }

}

/**
 * CRUDMessageServiceUser - сервис для обработки событий CRUD (Create, Read, Update, Delete)
 */

/**
 * Отправляются в Kafka с помощью продюсеров insertProducer и deleteProducer
 * Линкуют или разлинковывают сущности друг с другом (например, договоры и документы)
 */