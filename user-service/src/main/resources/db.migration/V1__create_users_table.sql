--changeset Abramov_Vladislav:V1__create_users_table

CREATE SCHEMA IF NOT EXISTS user_service;

--таблица пользователей
CREATE TABLE IF NOT EXISTS user_service.users
(
    user_id UUID PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    user_last_name VARCHAR(30) NOT NULL,
    user_first_name VARCHAR(30) NOT NULL
);