package org.abramov.spring.testovoe.taskservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.abramov.spring.testovoe.taskservice.enums.TaskStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Column(name = "task_name",nullable = false)
    private String taskName;

    @Column(name = "owner_user_id",nullable = false)
    private UUID taskOwnerId;

    @Column(name = "executor_user_id",nullable = false)
    private UUID taskExecutorId;

    @Column(name = "task_status",nullable = false)
    private TaskStatus taskStatus;

    @Column(name = "create_date",nullable = false)
    private LocalDateTime taskCreateDate;

    @Column(name = "update_date",nullable = false)
    private LocalDateTime taskUpdateDate;
}
