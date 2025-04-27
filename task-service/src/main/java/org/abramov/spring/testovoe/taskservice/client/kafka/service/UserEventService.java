package org.abramov.spring.testovoe.taskservice.client.kafka.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.taskservice.dto.UserCRUD;
import org.abramov.spring.testovoe.taskservice.entity.UserTask;
import org.abramov.spring.testovoe.taskservice.service.UserTaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventService {
    private final UserTaskService userTaskService;

    public void handleDeleteUsers(List<UserCRUD> users) {
        log.info("Удаление пользователей, количество: {}", users.size());

        users.stream()
                .filter(Objects::nonNull)
                .forEach(user -> {
                    if (userTaskService.exists(user.getId())) {
                        log.info("Удаляем пользователя: {}", user.getId());
                        userTaskService.removeUser(user.getId());
                    }
                });
    }

    public void handleUpdateUsers(List<UserCRUD> users) {
        log.info("Обновление пользователей, количество: {}", users.size());

        users.stream()
                .filter(Objects::nonNull)
                .forEach(user -> {
                    if (userTaskService.exists(user.getId())) {
                        UserTask updatedUser = UserTask.builder()
                                .userId(user.getId())
                                .username(user.getUsername())
                                .userFirstName(user.getUserFirstName())
                                .userLastName(user.getUserLastName())
                                .build();
                        log.info("Обновляем пользователя: {}", updatedUser);
                        userTaskService.saveOrUpdateUser(updatedUser);
                    }
                });
    }

}

