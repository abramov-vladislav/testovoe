package org.abramov.spring.testovoe.userservice.client.kafka.producer;


import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.client.kafka.model.message.CRUDMessageUser;
import org.abramov.spring.testovoe.userservice.dto.UserCRUD;
import org.abramov.spring.testovoe.userservice.enums.EventTypeUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CRUDProducerUser {

    private final KafkaTemplate<String, CRUDMessageUser> kafkaTemplate;

    @Value("${spring.kafka.topic.user-insert.name}")
    private String insertTopic;

    @Value("${spring.kafka.topic.user-delete.name}")
    private String deleteTopic;

    @Value("${spring.kafka.topic.user-update.name}")
    private String updateTopic;

    public void send(List<UserCRUD> objects, EventTypeUser eventType) {
        var message = new CRUDMessageUser(objects, eventType);
        String topic = switch (eventType) {
            case USER_CREATE -> insertTopic;
            case USER_DELETE -> deleteTopic;
            case USER_UPDATE -> updateTopic;
            default -> throw new IllegalArgumentException("Unsupported type: " + eventType);
        };
        kafkaTemplate.send(topic, message);
    }
}

/**
 * Отправляет CRUD-события (Create, Read, Update, Delete) о пользователях.
 */
