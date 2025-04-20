CREATE SCHEMA IF NOT EXISTS task_service;

CREATE TYPE task_service.status AS ENUM (
    'NEW',
    'ASSIGNED',
    'IN_PROGRESS',
    'COMPLETED'
    );

CREATE TABLE IF NOT EXISTS task_service.tasks
(
    task_id          UUID PRIMARY KEY,
    task_name        VARCHAR(255) NOT NULL,
    owner_user_id    UUID         NOT NULL,
    executor_user_id UUID         NOT NULL,
    task_status      VARCHAR(20),
    task_create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    task_update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_owner FOREIGN KEY (owner_user_id) REFERENCES user_service.users (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_executor FOREIGN KEY (executor_user_id) REFERENCES user_service.users (user_id) ON DELETE CASCADE
);


