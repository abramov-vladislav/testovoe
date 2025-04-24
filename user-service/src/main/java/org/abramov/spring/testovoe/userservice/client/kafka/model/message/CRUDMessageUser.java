package org.abramov.spring.testovoe.userservice.client.kafka.model.message;

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
public class CRUDMessageUser {

    private UUID id;             // ID пользователя
    private String username;     // Логин пользователя
    private String fullName;     // ФИО пользователя (можно склеить фамилию и имя)
    private OperationType operation; // Тип операции: INSERT, UPDATE, DELETE

    public boolean isValid() {
        return id != null && username != null && !username.isBlank() && operation != null;
    }

    public enum OperationType {
        INSERT, UPDATE, DELETE
    }
}
