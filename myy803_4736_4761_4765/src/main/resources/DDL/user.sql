--CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `USER_`;

CREATE TABLE `USER_` (
    us_id int(10) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE,
    password_ VARCHAR(255) DEFAULT NULL,
    role_ VARCHAR(10) DEFAULT NULL
);


