package org.abramov.spring.testovoe.userservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

//DTO (Data Transfer Objects) – объекты для передачи данных
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID userId;
    private String userLogin;
    private String fullName; // Конкатенация фамилии и имени

    // Метод преобразования User в UserDto
    public static UserDTO from(org.abramov.spring.testovoe.userservice.model.User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserLogin(),
                user.getUserLastName() + " " + user.getUserName() // Объединяем фамилию и имя
        );
    }

}

/*
Продумай, где и как будет происходить преобразование
из сущности в DTO (например, в сервисном слое или с помощью mapper-библиотеки, такой как MapStruct).
 */