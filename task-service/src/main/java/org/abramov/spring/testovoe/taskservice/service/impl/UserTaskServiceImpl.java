package org.abramov.spring.testovoe.taskservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.taskservice.entity.UserTask;
import org.abramov.spring.testovoe.taskservice.repository.UserTaskRepository;
import org.abramov.spring.testovoe.taskservice.service.UserTaskService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTaskServiceImpl implements UserTaskService {

    private final UserTaskRepository userTaskRepository;

    @Override
    public void updateUser(UserTask userTask) {
        if (userTask != null && userTask.getUserId() != null) {
            UserTask userUpdate = UserTask.builder()
                    .userId(userTask.getUserId())
                    .username(userTask.getUsername())
                    .userFirstName(userTask.getUserFirstName())
                    .userLastName(userTask.getUserLastName())
                    .build();

            userTaskRepository.updateUser(userUpdate);

            log.info("Пользователь сохранён или обновлён в БД: {}", userTask);
        } else {
            log.warn("Попытка сохранить некорректного пользователя: {}", userTask);
        }
    }

    @Override
    public void removeUser(UUID userId) {
        userTaskRepository.deleteById(userId);

        log.info("Пользователь с id {} удалён из БД", userId);
    }

    @Override
    public boolean exists(UUID userId) {
        return userTaskRepository.existsById(userId);
    }
}