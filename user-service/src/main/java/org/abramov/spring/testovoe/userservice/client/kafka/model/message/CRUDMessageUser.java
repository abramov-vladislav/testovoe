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

    private UUID id;
    private String username;
    private String fullName;
    private OperationType operation;

    public boolean isValid() {
        return id != null && username != null && !username.isBlank() && operation != null;
    }

    public enum OperationType {
        INSERT, UPDATE, DELETE
    }
}
