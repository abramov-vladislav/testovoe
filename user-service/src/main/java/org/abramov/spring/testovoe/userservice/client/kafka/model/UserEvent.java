package org.abramov.spring.testovoe.userservice.client.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.abramov.spring.testovoe.userservice.enums.EventTypeUser;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {

    private EventTypeUser eventTypeUser;

    private UUID objectId;
}
