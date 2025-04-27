package org.abramov.spring.testovoe.taskservice.client.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.taskservice.entity.UserTask;
import org.abramov.spring.testovoe.taskservice.repository.UserTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTaskService {

    private final UserTaskRepository userTaskRepository;

    @Transactional
    public void saveOrUpdateUser(UserTask userTask) {
        if (userTask != null && userTask.getUserId() != null) {
            UserTask entity = UserTask.builder()
                    .userId(userTask.getUserId())
                    .username(userTask.getUsername())
                    .userFirstName(userTask.getUserFirstName())
                    .userLastName(userTask.getUserLastName())
                    .build();

            userTaskRepository.save(entity);
            log.info("Пользователь сохранён или обновлён в БД: {}", userTask);
        } else {
            log.warn("Попытка сохранить некорректного пользователя: {}", userTask);
        }
    }

    @Transactional(readOnly = true)
    public UserTask getUserById(UUID userId) {
        return userTaskRepository.findById(userId)
                .map(entity -> UserTask.builder()
                        .userId(entity.getUserId())
                        .username(entity.getUsername())
                        .userFirstName(entity.getUserFirstName())
                        .userLastName(entity.getUserLastName())
                        .build())
                .orElseGet(() -> {
                    log.warn("Пользователь с id {} не найден в БД", userId);
                    return null;
                });
    }

    @Transactional
    public void removeUser(UUID userId) {
        userTaskRepository.deleteById(userId);
        log.info("Пользователь с id {} удалён из БД", userId);
    }

    @Transactional(readOnly = true)
    public boolean exists(UUID userId) {
        return userTaskRepository.existsById(userId);
    }
}