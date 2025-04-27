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
public class UpdateUserConsumer {

    private final UserEventService userEventService;

    @KafkaListener(topics = "${spring.kafka.topic.user-update.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleUpdateMessage(UserMessage message) {
        if (message.getObjects() != null && !message.getObjects().isEmpty()) {
            userEventService.handleUpdateUsers(message.getObjects());
        }
    }

    /**
     * слшуать кафку и при получении инфо об изменениях в user-service сущности юзер, сообщать в UserTaskService инфо:
     *
     * если есть юзер, связанный с заданием,
     * у которого поля равны полям этого юзера, то
     * обнови инфо в заданиях (обнови поля) и обнови его в бд user_task
     */

    /**
     * Kafka Topic -> Consumer (DeleteUserConsumer или UpdateUserConsumer) ->
     * Парсинг сообщения в UserMessage ->
     * UserEventService ->
     * UserTaskService ->
     * Работа с базой через JPA сущность UserTask
     */
}
