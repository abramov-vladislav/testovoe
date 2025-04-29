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


