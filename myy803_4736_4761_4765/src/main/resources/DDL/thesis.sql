CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

--USE `diploma_management_app_db`;

--DROP TABLE IF EXISTS `THESIS`;

CREATE TABLE `THESIS` (
    th_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    st_username VARCHAR(50) NOT NULL,
    subject_id INT(50) NOT NULL,
    grade FLOAT(50) DEFAULT NULL,
    FOREIGN KEY (st_username) REFERENCES STUDENT(username),
    FOREIGN KEY (subject_id) REFERENCES SUBJECT(sub_id)
);