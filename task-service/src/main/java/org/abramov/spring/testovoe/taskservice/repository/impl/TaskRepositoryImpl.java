package org.abramov.spring.testovoe.taskservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

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
            ;
            task.setTaskUpdateDate(rs.getDate("task_update_date").toLocalDate());
            tasks.add(task);
        }
        return tasks;
    };


    @Override
    public List<Task> findAll(Integer pageNumber, Integer pageSize) {

        return null;
    }

    @Override
    public List<Task> findAllByUserId(UUID userId, Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        int limit = pageSize;

        String sql = """
                
                """;

        final var args = new Object[]{limit, offset};
        final var types = new int[]{Types.INTEGER, Types.INTEGER};

        return Objects.requireNonNull(jdbcTemplate.query(sql, args, types, taskExtractor));
    }

    @Override
    public Task findById(UUID taskId) {
        //FIXME: Найти по айди

        return null;
    }

    @Override
    public Task save(Task task) {
        //FIXME: Сохранить в бд

        return null;
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


}
