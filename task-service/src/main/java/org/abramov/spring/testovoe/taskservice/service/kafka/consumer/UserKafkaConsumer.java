package org.abramov.spring.testovoe.taskservice.service.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.abramov.spring.testovoe.userservice.dto.response.UserDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserKafkaConsumer {

    private final TaskRepository taskRepository;

    @KafkaListener(topics = "user-service.user.updated", groupId = "task-service-group")
    public void consumeUserUpdate(UserDto userDto) {
        List<Task> affectedTasks = taskRepository.findAllByUserId(userDto.getUserId());

        for (Task task : affectedTasks) {
            if (task.getTaskOwnerId().equals(userDto.getUserId())) {
                task.setTaskOwnerFullName(userDto.getUserLastName() + " " + userDto.getUserFirstName());
            }
            if (task.getTaskExecutorId().equals(userDto.getUserId())) {
                task.setTaskExecutorFullName(userDto.getUserLastName() + " " + userDto.getUserFirstName());
            }
            task.setTaskUpdateDate(LocalDateTime.now());
            taskRepository.updateTask(task);
        }
    }
}

