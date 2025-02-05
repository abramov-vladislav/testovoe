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

    /* должен поддерживать операции:
получить всех пользователей,
получить пользователя по идентификатору,
получить пользователя по его логину (username)
редактировать пользователя
     */

    /* операции записи:
идентификация пользователя при редактирования осуществляется через его идентификатор
при редактировании указываются свойства: логин, фамилия, имя
при редактировании запрос может быть отклонен если новый логин не является старым и при этом он не уникален
     */
}
