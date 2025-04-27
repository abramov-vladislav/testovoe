package org.abramov.spring.testovoe.taskservice.client.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.abramov.spring.testovoe.taskservice.dto.UserCRUD;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserMessage {

    private List<UserCRUD> objects;
}

/**
 * Модель для получения сообщения из Kafka
 */