package org.abramov.spring.testovoe.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

//сущность
@Setter
@Getter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //свойства пользователя

    @Id
    private UUID userId;

    private String username;
    private String userLastName;
    private String userFirstName;
}
/*
Класс User содержит нужные поля.
Замечания:

Отсутствуют аннотации JPA (@Entity, @Table).
Для работы с базой данных нужно добавить их.
Рекомендуется также добавить аннотацию
@Column для указания уникальности логина (например, @Column(unique = true)).
 */
