--CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `APPLICATION`;

CREATE TABLE `APPLICATION` (
    ap_id int(10) PRIMARY KEY AUTO_INCREMENT,
    subject_id INT(50) NOT NULL,
    st_username VARCHAR(50) NOT NULL,
    FOREIGN KEY (st_username) REFERENCES `STUDENT`(username),
    FOREIGN KEY (subject_id) REFERENCES `SUBJECT`(sub_id)
);