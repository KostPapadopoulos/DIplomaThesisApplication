--CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `APPLICATION`;

CREATE TABLE `APPLICATION` (
    ap_id int(10) PRIMARY KEY AUTO_INCREMENT,
    subject_id INT(50) DEFAULT NULL,
    student_id INT(50) DEFAULT NULL,
    FOREIGN KEY (student_id) REFERENCES `STUDENT`(st_id),
    FOREIGN KEY (subject_id) REFERENCES `SUBJECT`(sub_id)
);