--changeset Abramov_Vladislav:V2__add_users

INSERT INTO task_service.tasks (task_id, task_name, owner_user_id, executor_user_id, task_status)
VALUES
    (gen_random_uuid(), 'Разработать REST API',
     (SELECT user_id FROM user_service.users WHERE username = 'popov'),
     (SELECT user_id FROM user_service.users WHERE username = 'volkova'),
     'NEW'),

    (gen_random_uuid(), 'Написать unit-тесты',
     (SELECT user_id FROM user_service.users WHERE username = 'volkova'),
     (SELECT user_id FROM user_service.users WHERE username = 'novikov'),
     'ASSIGNED'),

    (gen_random_uuid(), 'Создать фронтенд интерфейс',
     (SELECT user_id FROM user_service.users WHERE username = 'novikov'),
     (SELECT user_id FROM user_service.users WHERE username = 'fedorova'),
     'IN_PROGRESS'),

    (gen_random_uuid(), 'Оптимизировать базу данных',
     (SELECT user_id FROM user_service.users WHERE username = 'fedorova'),
     (SELECT user_id FROM user_service.users WHERE username = 'morozov'),
     'COMPLETED'),

    (gen_random_uuid(), 'Настроить CI/CD',
     (SELECT user_id FROM user_service.users WHERE username = 'morozov'),
     (SELECT user_id FROM user_service.users WHERE username = 'popov'),
     'NEW'),

    (gen_random_uuid(), 'Реализовать аутентификацию',
     (SELECT user_id FROM user_service.users WHERE username = 'popov'),
     (SELECT user_id FROM user_service.users WHERE username = 'fedorova'),
     'ASSIGNED'),

    (gen_random_uuid(), 'Проектирование архитектуры',
     (SELECT user_id FROM user_service.users WHERE username = 'volkova'),
     (SELECT user_id FROM user_service.users WHERE username = 'morozov'),
     'IN_PROGRESS'),

    (gen_random_uuid(), 'Документирование API',
     (SELECT user_id FROM user_service.users WHERE username = 'novikov'),
     (SELECT user_id FROM user_service.users WHERE username = 'popov'),
     'COMPLETED'),

    (gen_random_uuid(), 'Рефакторинг кода',
     (SELECT user_id FROM user_service.users WHERE username = 'fedorova'),
     (SELECT user_id FROM user_service.users WHERE username = 'volkova'),
     'NEW'),

    (gen_random_uuid(), 'Интеграция с платежной системой',
     (SELECT user_id FROM user_service.users WHERE username = 'morozov'),
     (SELECT user_id FROM user_service.users WHERE username = 'novikov'),
     'ASSIGNED');