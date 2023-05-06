CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `THESIS`;

CREATE TABLE `THESIS` (
    th_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    student_id INT(50) DEFAULT NULL,
    subject_id INT(50) DEFAULT NULL,
    FOREIGN KEY (student_id) REFERENCES STUDENT(st_id),
    FOREIGN KEY (subject_id) REFERENCES SUBJECT(sub_id)
);