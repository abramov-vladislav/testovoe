package org.abramov.spring.testovoe.taskservice.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.taskservice.entity.UserTask;
import org.abramov.spring.testovoe.taskservice.repository.UserTaskRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserTaskRepositoryImpl implements UserTaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void updateUser(UserTask userUpdate) {
        String sql = """
                UPDATE task_service.user_task
                SET username = ?, user_first_name = ?, user_last_name = ?, user_status = ?
                WHERE user_id = ?
                """;

        int rows = jdbcTemplate.update(sql,
                userUpdate.getUsername(),
                userUpdate.getUserFirstName(),
                userUpdate.getUserLastName(),
                "EXIST",
                userUpdate.getUserId()
        );

        if (rows > 0) {
            log.info("Пользователь с id {} успешно обновлён в БД", userUpdate.getUserId());
        } else {
            log.warn("Не удалось обновить пользователя с id {} — запись не найдена", userUpdate.getUserId());
        }
    }


    @Override
    public void deleteById(UUID userId) {
        String sql = """
                UPDATE task_service.user_task
                SET user_status = ?
                WHERE user_id = ?
                """;

        int rows = jdbcTemplate.update(sql, "DELETED", userId);

        if (rows > 0) {
            log.info("Пользователь с id {} помечен как удалённый в БД", userId);
        } else {
            log.warn("Не удалось пометить пользователя с id {} как удалённого — запись не найдена", userId);
        }
    }


    @Override
    public boolean existsById(UUID userId) {
        String sql = """
                SELECT COUNT(*)
                FROM task_service.user_task 
                WHERE user_id = ? AND user_status = 'EXIST'
                """;

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);

        boolean exists = count != null && count > 0;

        log.debug("Проверка существования пользователя с id {}: {}", userId, exists ? "найден" : "не найден");

        return exists;
    }
}
