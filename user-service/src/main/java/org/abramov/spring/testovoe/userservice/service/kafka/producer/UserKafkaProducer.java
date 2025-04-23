package org.abramov.spring.testovoe.userservice.service.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserKafkaProducer {

    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public void sendUserUpdate(UserDto userDto) {
        log.info("Отправка события: {}", userDto);
        kafkaTemplate.send("user-service.user.updated", userDto.getUserId().toString(), userDto);
    }
}