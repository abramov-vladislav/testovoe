package org.abramov.spring.testovoe.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserCRUD {

    private UUID id;
    private String username;
    private String userFirstName;
    private String userLastName;

    public boolean isValid() {
        return this.getId() != null
                && this.getUsername() != null && !this.getUsername().isBlank()
                && this.getUserFirstName() != null && !this.getUserFirstName().isBlank()
                && this.getUserLastName() != null && !this.getUserLastName().isBlank();
    }
}

/**
 * Класс UserCRUD — это DTO (Data Transfer Object), который ты используешь для передачи информации
 * о пользователях внутри Kafka-сообщений, когда происходит операция создания, обновления или удаления пользователя.
 * <p>
 * Он не участвует напрямую в REST-контроллерах или в базе данных —
 * он предназначен для Kafka-сообщений и межсервисного взаимодействия.
 */