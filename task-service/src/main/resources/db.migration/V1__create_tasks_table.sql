--changeset Abramov_Vladislav:V1__create_tasks_table

CREATE SCHEMA IF NOT EXISTS task_service;

CREATE TYPE task_service.task_status AS ENUM (
    'NEW',
    'ASSIGNED',
    'IN_PROGRESS',
    'COMPLETED'
    );

CREATE TYPE task_service.user_status AS ENUM (
    'EXIST',
    'DELETED'
    );

--таблица хранения копий пользователей
CREATE TABLE IF NOT EXISTS task_service.user_task
(
    user_id         UUID PRIMARY KEY,
    username        VARCHAR                  NOT NULL,
    user_last_name  VARCHAR                  NOT NULL,
    user_first_name VARCHAR                  NOT NULL,
    user_status     task_service.user_status NOT NULL
);

--таблица задач
CREATE TABLE IF NOT EXISTS task_service.tasks
(
    task_id          UUID PRIMARY KEY,
    task_name        VARCHAR(100)             NOT NULL,
    owner_user_id    UUID                     NOT NULL,
    executor_user_id UUID                     NOT NULL,
    task_status      task_service.task_status NOT NULL DEFAULT 'NEW',
    task_create_date TIMESTAMP                         DEFAULT CURRENT_TIMESTAMP,
    task_update_date TIMESTAMP                         DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_owner FOREIGN KEY (owner_user_id) REFERENCES task_service.user_task (user_id),
    CONSTRAINT fk_executor FOREIGN KEY (executor_user_id) REFERENCES task_service.user_task (user_id)
);



