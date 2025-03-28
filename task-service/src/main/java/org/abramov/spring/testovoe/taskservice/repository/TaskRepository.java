package org.abramov.spring.testovoe.taskservice.repository;

import org.abramov.spring.testovoe.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
