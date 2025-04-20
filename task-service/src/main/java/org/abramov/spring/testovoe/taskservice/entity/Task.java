package org.abramov.spring.testovoe.taskservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tasks", schema = "task_service")
@Accessors(chain = true)
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "task_id")
    private UUID taskId;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "owner_user_id", nullable = false)
    private UUID taskOwnerId;
//FIXME: Не просто айдишники, а прям сущности

    @Column(name = "executor_user_id", nullable = false)
    private UUID taskExecutorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status", nullable = false)
    private TaskStatus taskStatus;

    @Column(name = "task_create_date", nullable = false)
    private LocalDate taskCreateDate;

    @Column(name = "task_update_date", nullable = false)
    private LocalDate taskUpdateDate;
}

