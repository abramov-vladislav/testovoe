package org.abramov.spring.testovoe.taskservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_task", schema = "task_service")
@Accessors(chain = true)
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "user_last_name", nullable = false)
    private String userLastName;

    @Column(name = "user_first_name", nullable = false)
    private String userFirstName;

}
