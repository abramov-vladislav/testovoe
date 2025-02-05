package org.abramov.spring.testovoe.taskservice.repository;
import org.abramov.spring.testovoe.taskservice.model.Task;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findByTaskId (UUID taskId);

}
