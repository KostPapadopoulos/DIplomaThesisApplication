--CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

--USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `STUDENT`;

CREATE TABLE `STUDENT` (
    username VARCHAR(40) PRIMARY KEY,
    email VARCHAR(40) UNIQUE,
    full_name VARCHAR(100) DEFAULT NULL,
    year_of_studies INT(2) DEFAULT NULL,
    current_avg_grade FLOAT(40)  DEFAULT NULL,
    number_of_courses INT(2) DEFAULT NULL
);
