package org.abramov.spring.testovoe.taskservice.client.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.taskservice.client.kafka.model.UserMessage;
import org.abramov.spring.testovoe.taskservice.client.kafka.service.UserEventService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteUserConsumer {

    private final UserEventService userEventService;

    @KafkaListener(topics = "${spring.kafka.topic.user-delete.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleDeleteMessage(UserMessage message) {
        if (message.getObjects() != null && !message.getObjects().isEmpty()) {
            userEventService.handleDeleteUsers(message.getObjects());
        }
    }
}

/**
 * слшуать кафку и при получении инфо об удалении в user-service сущности юзер, сообщать в UserTaskService инфо:
 * <p>
 * если есть юзер, связанный с заданием,
 * у которого поля равны полям этого юзера, то
 * обнови инфо в заданиях (удали из задания) и удали его в бд user_task
 */

