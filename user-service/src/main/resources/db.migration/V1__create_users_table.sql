CREATE SCHEMA IF NOT EXISTS user_service;

CREATE TABLE IF NOT EXISTS user_service.users
(
    user_id UUID PRIMARY KEY,
    login VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    name VARCHAR NOT NULL
);