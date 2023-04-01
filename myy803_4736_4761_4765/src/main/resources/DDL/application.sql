CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `APPLICATION`;

CREATE TABLE `APPLICATION` (
    ap_id int(10) PRIMARY KEY AUTO_INCREMENT,
    subject VARCHAR(50) DEFAULT NULL,
    student VARCHAR(50) DEFAULT NULL
);