package org.abramov.spring.testovoe.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.abramov.spring.testovoe.userservice.enums.EventTypeUser;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserCRUD {

    private UUID id;
    private String username;
    private String userFirstName;
    private String userLastName;
    private EventTypeUser eventType;

}

/**
 * Класс UserCRUD — это DTO (Data Transfer Object), который ты используешь для передачи информации
 * о пользователях внутри Kafka-сообщений, когда происходит операция создания, обновления или удаления пользователя.
 * <p>
 * Он не участвует напрямую в REST-контроллерах или в базе данных —
 * он предназначен для Kafka-сообщений и межсервисного взаимодействия.
 */