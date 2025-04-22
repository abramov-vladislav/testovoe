--changeset Abramov_Vladislav:V1__create_tasks_table

CREATE SCHEMA IF NOT EXISTS task_service;

CREATE TYPE task_service.status AS ENUM (
    'NEW',
    'ASSIGNED',
    'IN_PROGRESS',
    'COMPLETED'
    );

CREATE TABLE IF NOT EXISTS task_service.tasks
(
    task_id UUID PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL,
    owner_user_id UUID,
    executor_user_id UUID,
    task_status VARCHAR(20),
    task_create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    task_update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_owner FOREIGN KEY (owner_user_id) REFERENCES task_service.user_task (user_id) ON DELETE CASCADE, --удалить задачи, при удалении пользователя
    CONSTRAINT fk_executor FOREIGN KEY (executor_user_id) REFERENCES task_service.user_task (user_id) ON DELETE CASCADE --удалить задачи, при удалении пользователя
);

CREATE TABLE IF NOT EXISTS task_service.user_task
(
    user_id UUID PRIMARY KEY,
    username VARCHAR NOT NULL,
    user_last_name VARCHAR NOT NULL,
    user_first_name VARCHAR NOT NULL
);
