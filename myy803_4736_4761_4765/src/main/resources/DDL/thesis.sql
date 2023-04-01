CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `THESIS`;

CREATE TABLE `THESIS` (
    th_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    supervisor VARCHAR(50) DEFAULT NULL,
    subject VARCHAR(50) DEFAULT NULL
);