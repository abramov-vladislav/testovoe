package org.abramov.spring.testovoe.userservice.client.kafka.producer;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.client.kafka.model.message.CRUDMessageUser;
import org.abramov.spring.testovoe.userservice.dto.UserCRUD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Observed
@Component
@RequiredArgsConstructor
public class InsertProducerUser {

    private final KafkaTemplate<String, CRUDMessageUser> kafkaTemplateObject;
    @Value("${spring.kafka.topic.testovoe.name}")
    String topic;

    public void sendCRUDMessage(List<UserCRUD> objects) {
        var object = new CRUDMessageUser(objects);
        kafkaTemplateObject.send(topic, object);
    }
}
