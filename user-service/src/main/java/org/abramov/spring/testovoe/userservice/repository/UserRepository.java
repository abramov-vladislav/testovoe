package org.abramov.spring.testovoe.userservice.repository;
import org.abramov.spring.testovoe.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserId (UUID userId);

    //ИСПРАВИТЬ ВСЕ
    <T> ScopedValue<T> findByUsername(String username);
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