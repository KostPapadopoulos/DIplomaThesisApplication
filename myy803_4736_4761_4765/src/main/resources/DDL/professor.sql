CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `PROFESSOR`;

CREATE TABLE `PROFESSOR` (
    pr_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) DEFAULT NULL,
    specialty VARCHAR(100) DEFAULT NULL,
    pr_password VARCHAR(100) NOT NULL
);