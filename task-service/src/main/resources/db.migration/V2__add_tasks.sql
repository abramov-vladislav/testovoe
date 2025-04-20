--changeset Abramov_Vladislav:V2__add_tasks

INSERT INTO task_service.tasks (task_id, task_name, owner_user_id, executor_user_id, task_status)
VALUES
    (gen_random_uuid(), 'Разработать REST API',
     (SELECT user_id FROM user_service.users WHERE username = 'ivanov'),
     (SELECT user_id FROM user_service.users WHERE username = 'petrov'),
     'NEW'),

    (gen_random_uuid(), 'Написать unit-тесты',
     (SELECT user_id FROM user_service.users WHERE username = 'petrov'),
     (SELECT user_id FROM user_service.users WHERE username = 'sidorov'),
     'ASSIGNED'),

    (gen_random_uuid(), 'Создать фронтенд интерфейс',
     (SELECT user_id FROM user_service.users WHERE username = 'sidorov'),
     (SELECT user_id FROM user_service.users WHERE username = 'smirnova'),
     'IN_PROGRESS'),

    (gen_random_uuid(), 'Оптимизировать базу данных',
     (SELECT user_id FROM user_service.users WHERE username = 'smirnova'),
     (SELECT user_id FROM user_service.users WHERE username = 'kuznetsov'),
     'COMPLETED'),

    (gen_random_uuid(), 'Настроить CI/CD',
     (SELECT user_id FROM user_service.users WHERE username = 'kuznetsov'),
     (SELECT user_id FROM user_service.users WHERE username = 'ivanov'),
     'NEW'),

    (gen_random_uuid(), 'Реализовать аутентификацию',
     (SELECT user_id FROM user_service.users WHERE username = 'ivanov'),
     (SELECT user_id FROM user_service.users WHERE username = 'smirnova'),
     'ASSIGNED'),

    (gen_random_uuid(), 'Проектирование архитектуры',
     (SELECT user_id FROM user_service.users WHERE username = 'petrov'),
     (SELECT user_id FROM user_service.users WHERE username = 'kuznetsov'),
     'IN_PROGRESS'),

    (gen_random_uuid(), 'Документирование API',
     (SELECT user_id FROM user_service.users WHERE username = 'sidorov'),
     (SELECT user_id FROM user_service.users WHERE username = 'ivanov'),
     'COMPLETED'),

    (gen_random_uuid(), 'Рефакторинг кода',
     (SELECT user_id FROM user_service.users WHERE username = 'smirnova'),
     (SELECT user_id FROM user_service.users WHERE username = 'petrov'),
     'NEW'),

    (gen_random_uuid(), 'Интеграция с платежной системой',
     (SELECT user_id FROM user_service.users WHERE username = 'kuznetsov'),
     (SELECT user_id FROM user_service.users WHERE username = 'sidorov'),
     'ASSIGNED');
