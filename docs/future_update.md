Чтобы при создании задания в `task_service.user_task` записывалась копия данных пользователя, тебе нужно выполнить несколько шагов:

1. **После создания задания вставить копию пользователя в таблицу `user_task`**.
2. **Для каждого пользователя (владельца и исполнителя) выполнить вставку в `user_task`**.

Ниже приведен пример того, как это можно реализовать.

### Шаг 1: Обновление метода создания задания в `TaskServiceImpl`

Тебе нужно обновить метод `createTask` в сервисе, чтобы после создания задания, также вставлялись данные пользователей в таблицу `user_task`.

### Обновленный код:

```java
@Transactional
@Override
public Task createTask(CreateTaskDto createTaskDto) {
    // Создание задания
    Task task = taskRepository.createTask(TaskMapper.toTask(createTaskDto));

    // Вставка данных владельца и исполнителя в таблицу user_task
    insertUserToTaskTable(task.getTaskOwnerId());
    insertUserToTaskTable(task.getTaskExecutorId());

    return task;
}

private void insertUserToTaskTable(UUID userId) {
    // Получение данных пользователя по его ID
    User user = userRepository.findById(userId); // Предполагается, что у тебя есть репозиторий для работы с пользователями

    if (user != null) {
        final String sql = """
                INSERT INTO task_service.user_task (user_id, username, user_last_name, user_first_name)
                VALUES (?, ?, ?, ?)
                ON CONFLICT (user_id) DO NOTHING; -- если пользователь уже есть, ничего не делать
                """;
        
        final Object[] args = new Object[]{
            user.getUserId(),
            user.getUsername(),
            user.getUserLastName(),
            user.getUserFirstName()
        };
        
        final int[] types = new int[]{
            Types.OTHER, // UUID
            Types.VARCHAR, // String
            Types.VARCHAR, // String
            Types.VARCHAR  // String
        };
        
        jdbcTemplate.update(sql, args, types); // Выполнение SQL запроса
    }
}
```

### Шаг 2: Обновление репозитория

Теперь необходимо сделать так, чтобы твой `TaskRepository` возвращал объект задачи, а также чтобы ты мог вставить пользователя в таблицу `user_task`.

Ты уже делаешь это в `TaskRepositoryImpl` с помощью `jdbcTemplate`. Теперь добавим вставку данных о пользователе в таблицу `user_task`.

### Обновленный код репозитория:

```java
@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    @Override
    public Task createTask(Task task) {
        final var sql = """
                INSERT INTO task_service.tasks
                (task_id, task_name, owner_user_id, executor_user_id, task_status, task_create_date, task_update_date)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        final var args = new Object[]{
            task.getTaskId(),
            task.getTaskName(),
            task.getTaskOwnerId(),
            task.getTaskExecutorId(),
            task.getTaskStatus(),
            task.getTaskCreateDate(),
            task.getTaskUpdateDate()
        };
        final var types = new int[]{
            Types.OTHER, // UUID
            Types.VARCHAR, // String
            Types.OTHER, // UUID
            Types.OTHER, // UUID
            Types.VARCHAR, // String
            Types.TIMESTAMP, // Date
            Types.TIMESTAMP  // Date
        };

        jdbcTemplate.update(sql, args, types);

        // Теперь возвращаем задание после его создания
        return task;
    }
}
```

### Шаг 3: Убедись, что у тебя есть репозиторий для пользователей

Так как в коде выше я использую `userRepository.findById(userId)`, тебе нужно создать репозиторий для пользователей, чтобы получать данные о пользователе для вставки в таблицу `user_task`.

Пример репозитория:

```java
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;

    public User findById(UUID userId) {
        final String sql = "SELECT * FROM user_service.users WHERE user_id = ?";
        
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) -> 
            new User(
                UUID.fromString(rs.getString("user_id")),
                rs.getString("username"),
                rs.getString("user_last_name"),
                rs.getString("user_first_name")
            )
        );
    }
}
```

### Шаг 4: Добавить обработку ошибок

Важно учесть, что при добавлении в таблицу `user_task` могут возникнуть конфликты, если такой пользователь уже существует. Чтобы предотвратить ошибку вставки, можно использовать конструкцию `ON CONFLICT (user_id) DO NOTHING`, которая гарантирует, что если пользователь с таким ID уже есть в таблице, то вставка не произойдет.

### SQL-запрос для вставки:

```sql
INSERT INTO task_service.user_task (user_id, username, user_last_name, user_first_name)
VALUES (?, ?, ?, ?)
ON CONFLICT (user_id) DO NOTHING;
```

### Резюме

- Ты создал задачу, а затем добавил копии пользователей (владельца и исполнителя) в таблицу `user_task`.
- Использован метод `insertUserToTaskTable`, который проверяет, есть ли уже пользователь в таблице `user_task`, и добавляет его, если его там нет.
- Добавлена защита от конфликтов при вставке данных с помощью SQL-конструкции `ON CONFLICT (user_id) DO NOTHING`.

Этот подход обеспечивает корректную работу системы, не нарушая основную логику создания задания.

Сервис       | Kafka Producer | Kafka Consumer | Что делает
user-service |       ✅       |       ❌       | Отправляет события при изменении юзера
task-service |       ❌       |       ✅       | Читает события и обновляет кэш