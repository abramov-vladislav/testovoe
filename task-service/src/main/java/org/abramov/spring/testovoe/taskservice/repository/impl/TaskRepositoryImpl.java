package org.abramov.spring.testovoe.taskservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final static ResultSetExtractor<List<Task>> taskExtractor = (rs) -> {
        final var tasks = new ArrayList<Task>();
        while (rs.next()) {
            final var task = new Task();
            task.setTaskId(rs.getObject("task_id", UUID.class));
            task.setTaskName(rs.getString("task_name"));
            task.setTaskOwnerId(rs.getObject("task_owner_id", UUID.class));
            task.setTaskExecutorId(rs.getObject("task_executor_id", UUID.class));
            task.setTaskStatus(TaskStatus.valueOf(rs.getString("task_status")));
            task.setTaskCreateDate(rs.getDate("task_create_date").toLocalDate());
            task.setTaskUpdateDate(rs.getDate("task_update_date").toLocalDate());
            tasks.add(task);
        }
        return tasks;
    };

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> getAllTasksByOwnerUserId(UUID userId, Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        int limit = pageSize;

        String sql = """
                SELECT *
                FROM task_service.tasks
                WHERE owner_user_id = ?
                LIMIT ? OFFSET ?
                """;

        final var args = new Object[]{userId, limit, offset};
        final var types = new int[]{Types.INTEGER, Types.INTEGER};

        return Objects.requireNonNull(jdbcTemplate.query(sql, args, types, taskExtractor));
    }

    @Override
    public List<Task> getAllTaskslByExecutorUserId(UUID userId, Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        int limit = pageSize;

        String sql = """
                SELECT *
                FROM task_service.tasks
                WHERE owner_user_id = ?
                LIMIT ? OFFSET ?
                """;

        final var args = new Object[]{userId, limit, offset};
        final var types = new int[]{Types.INTEGER, Types.INTEGER};

        return Objects.requireNonNull(jdbcTemplate.query(sql, args, types, taskExtractor));
    }

    @Override
    public boolean existsTaskByTaskName(String taskName) {
        String sql = """
                SELECT *
                FROM task_service.tasks
                WHERE task_name = ?
                """;

        return jdbcTemplate.query(sql, taskExtractor, taskName) != null;
    }

    @Override
    public void deleteTaskByTaskId(UUID taskId) {
        final var sql = """
                DELETE FROM task_service.tasks 
                WHERE task_id = ?
                """;

        jdbcTemplate.update(sql, taskId);
    }

    @Override
    public List<Task> getAllTasks(Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        int limit = pageSize;

        String sql = """
                SELECT *
                FROM task_service.tasks
                LIMIT ? OFFSET ?
                """;

        final var args = new Object[]{limit, offset};
        final var types = new int[]{Types.INTEGER, Types.INTEGER};

        return Objects.requireNonNull(jdbcTemplate.query(sql, args, types, taskExtractor));
    }

    @Override
    public Optional<Task> getTaskByTaskId(UUID taskId) {
        final var sql = """
                SELECT *
                FROM task_service.tasks
                WHERE task_id = ?
                """;

        final var tasks = Objects.requireNonNull(jdbcTemplate.query(sql, taskExtractor, taskId));

        return tasks.isEmpty() ? Optional.empty() : Optional.of(tasks.getFirst());
    }

    @Override
    public Task createTask(Task task) {
        final var sql = """
                INSERT INTO task_service.tasks 
                (task_id, task_name, owner_user_id, executor_user_id, task_status, create_date, update_date)
                VALUES (?, ?, ?, ?)
                """;

        final var args = new Object[]{task.getTaskId(), task.getTaskName(), task.getTaskOwnerId(),
                task.getTaskExecutorId(), task.getTaskStatus(), task.getTaskCreateDate(), task.getTaskUpdateDate()};
        final var types = new int[]{Types.OTHER, Types.VARCHAR, Types.OTHER, Types.OTHER, Types.VARCHAR,
                Types.TIMESTAMP, Types.TIMESTAMP};
        jdbcTemplate.update(sql, args, types);

        return task;
    }


}
