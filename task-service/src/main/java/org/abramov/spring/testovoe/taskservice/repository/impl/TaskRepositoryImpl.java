package org.abramov.spring.testovoe.taskservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.controller.enums.TaskStatus;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.Types;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    private final static ResultSetExtractor<List<Task>> taskExtractor = (rs) -> {
        final var tasks = new ArrayList<Task>();
        while (rs.next()) {
            final var task = new Task();

            task.setTaskId(rs.getObject("task_id", UUID.class));
            task.setTaskName(rs.getString("name"));
            task.setTaskOwnerId(rs.getObject("owner_user_id", UUID.class));
            task.setTaskExecutorId(rs.getObject("executor_user_id", UUID.class));
            task.setTaskStatus(TaskStatus.valueOf(rs.getString("status")));
            task.setTaskCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
            task.setTaskUpdateDate(rs.getTimestamp("update_date").toLocalDateTime());

            tasks.add(task);
        }
        return tasks;
    };


    @Override
    public List<Task> getAllTasks(Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        int limit = pageSize;

        final var sql = """
                SELECT *
                FROM tasks
                LIMIT ? OFFSET ?
                """;

        final var args = new Object[]{ offset, limit };
        final var types = new int[]{ Types.INTEGER, Types.INTEGER };

        return Objects.requireNonNull(jdbcTemplate.query(sql, args, types, taskExtractor));
    }

    @Override
    public Optional<Task> getTaskByTaskId(UUID taskId) {
        final var sql = """
                SELECT *
                FROM tasks
                WHERE task_id = ?
                """;
        final var args = new Object[]{ taskId };
        final var tasks = Objects.requireNonNull(jdbcTemplate.query(sql, args, taskExtractor));

        return tasks.isEmpty() ? Optional.empty() : Optional.of(tasks.getFirst());
    }

    @Override
    public Task createTask(Task task) {
        return null;
        //
         //
         //
         //
    }

    @Override
    public Task updateTask(Task task) {
        return null;
        //
        //
        //
        //
    }
}
