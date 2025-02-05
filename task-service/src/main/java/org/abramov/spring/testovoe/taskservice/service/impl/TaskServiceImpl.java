package org.abramov.spring.testovoe.taskservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.taskservice.repository.TaskRepository;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task getTaskByTaskId(UUID taskId) {
        return taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}


/*
Нет полной реализации API

В тестовом задании требуется поддержка множества операций (CRUD),
а у тебя реализован только один метод getTaskByTaskId(UUID taskId).

Нужно добавить:
Получение всех задач.
Получение списка задач владельца.
Получение списка задач, назначенных пользователю.
Создание, редактирование, удаление задачи.
Изменение статуса.
 */

/*
Нет обработки ролей пользователей (владелец, исполнитель)

Согласно ТЗ, доступ к задачам ограничен:
Владелец может делать всё (читать, редактировать, удалять, менять статус).
Исполнитель может только смотреть задачу и менять статус.
Остальные пользователи не должны иметь доступ.
Нужно добавить проверку ролей при выполнении операций.
 */

/*
Нет работы с пользователями (owner/assignee)

Согласно требованиям, в ответах на запросы должны возвращаться полные данные о владельце и исполнителе, а у тебя в Task этого нет.
Либо добавь в Task поля owner и assignee с их свойствами, либо используй DTO для возврата задачи с полными данными.
Нет слоёв DTO и мапперов

Если сервис отдаёт Task напрямую, это плохо.
Нужно создать TaskDTO, который содержит:
Полные данные о владельце и исполнителе.
Можно ли выполнять действия с задачей (например, canEdit).
Нет транзакций

Добавь @Transactional для методов, которые изменяют данные (updateTask, deleteTask, changeStatus).
Исключения

Использовать RuntimeException — плохая практика. Лучше создать кастомные исключения (TaskNotFoundException).
 */