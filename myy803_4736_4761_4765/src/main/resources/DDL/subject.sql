CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `STUDENT`;

CREATE TABLE `SUBJECT` (
    sub_id int(2) PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) DEFAULT NULL,
    objective VARCHAR(1000) DEFAULT NULL,
    sub_availability BOOLEAN DEFAULT FALSE,
    professor_id INT(10) ,
    applications VARCHAR(1000) DEFAULT NULL,
    FOREIGN KEY (professor_id) REFERENCES `PROFESSOR`(pr_id)
);