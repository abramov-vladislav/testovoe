package org.abramov.spring.testovoe.userservice.client.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.client.kafka.model.UserEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventProducerUser {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;
    @Value("${spring.kafka.topic.event.name}")
    private String topicName;

    public void sendMessage(UserEvent userEvent) {
        kafkaTemplate.send(topicName, userEvent);
    }


}
