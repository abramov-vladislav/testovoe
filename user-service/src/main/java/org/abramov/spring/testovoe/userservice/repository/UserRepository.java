package org.abramov.spring.testovoe.userservice.repository;
import org.abramov.spring.testovoe.userservice.model.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    Optional<User> findByUserId (UUID userId);

}

/*
Репозиторий:
UserRepository наследуется от JpaRepository и имеет метод findByUserId(UUID userId).
Замечания:

В JPA сущностях обычно используется поле id с аннотацией @Id.
Если планируешь использовать userId, то необходимо явно аннотировать его в сущности.
Желательно добавить метод для поиска по логину
(например, Optional<User> findByUserLogin(String userLogin)).
 */