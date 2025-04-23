package org.abramov.spring.testovoe.userservice.service.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserKafkaProducer {

    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public void sendUserUpdate(UserDto userDto) {
        kafkaTemplate.send("user-service.user.updated", userDto.getUserId().toString(), userDto);
    }
}