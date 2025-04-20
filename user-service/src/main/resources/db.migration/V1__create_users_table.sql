CREATE SCHEMA IF NOT EXISTS user_service;

CREATE TABLE IF NOT EXISTS user_service.users
(
    user_id         UUID PRIMARY KEY,
    username        VARCHAR NOT NULL,
    user_last_name  VARCHAR NOT NULL,
    user_first_name VARCHAR NOT NULL
);