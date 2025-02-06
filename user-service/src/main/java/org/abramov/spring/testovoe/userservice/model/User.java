package org.abramov.spring.testovoe.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

//сущность
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //свойства пользователя
    private UUID userId;
    private String userLogin;
    private String userLastName;
    private String userName;
}
/*
Класс User содержит нужные поля.
Замечания:

Отсутствуют аннотации JPA (@Entity, @Table).
Для работы с базой данных нужно добавить их.
Рекомендуется также добавить аннотацию
@Column для указания уникальности логина (например, @Column(unique = true)).
 */
