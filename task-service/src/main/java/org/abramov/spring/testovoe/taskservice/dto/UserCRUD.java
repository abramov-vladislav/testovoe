package org.abramov.spring.testovoe.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.abramov.spring.testovoe.taskservice.enums.EventTypeUser;

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
