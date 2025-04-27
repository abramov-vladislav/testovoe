package org.abramov.spring.testovoe.taskservice.client.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.taskservice.entity.UserTask;
import org.abramov.spring.testovoe.taskservice.client.kafka.service.UserTaskService;
import org.abramov.spring.testovoe.taskservice.repository.UserTaskRepository;
import org.abramov.spring.testovoe.userservice.enums.EventTypeUser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserConsumer {

    private final UserTaskService userTaskService;
    private final UserTaskRepository userTaskRepository;  // Для работы с базой данных

    @KafkaListener(topics = "${kafka.topics.user-topic}", groupId = "${kafka.consumer.group-id}")
    public void consumeUser(UserTask userTask) {
        log.info("Получено сообщение о пользователе: {}", userTask);

        // Если это сообщение об удалении пользователя
        if (EventTypeUser.USER_DELETE.equals(userTask.getEventTypeUser())) {
            log.info("Удаление пользователя с ID: {}", userTask.getUserId());

            /**
             * в чем суть строчки  if (EventTypeUser.USER_DELETE.equals(userTask.getEventTypeUser())) {
             */
            // Удаляем пользователя из бд
            userTaskService.removeUser(userTask.getUserId());
        } else {
            /**
             * должна быть связь с заданием, то есть, если мы обновляем сущность юзера,
             * то и в задании тоже нужно обновить
             *
             * и по-моему нужно только обновлять сущность юзера
             * (нам неинтересно, если пользователя создали, он же не связан с сервисом таск)
             */
            // Обрабатываем обновление пользователя
            userTaskService.saveOrUpdateUser(userTask);
        }
    }
}
