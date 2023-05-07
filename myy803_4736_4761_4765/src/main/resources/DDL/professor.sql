--CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

--USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `PROFESSOR`;

CREATE TABLE `PROFESSOR` (
    username VARCHAR(40) PRIMARY KEY,
    full_name VARCHAR(100) DEFAULT NULL,
    specialty VARCHAR(100) DEFAULT NULL
);