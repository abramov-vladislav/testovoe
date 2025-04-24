package org.abramov.spring.testovoe.taskservice.client.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.abramov.spring.testovoe.taskservice.enums.EventTypeTask;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEvent {

    private EventTypeTask eventTypeTask;

    private UUID objectId;
}
