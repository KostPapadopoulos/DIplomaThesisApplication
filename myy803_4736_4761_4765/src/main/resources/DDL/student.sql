--CREATE DATABASE IF NOT EXISTS `diploma_management_app_db`;

USE `diploma_management_app_db`;

DROP TABLE IF EXISTS `STUDENT`;

CREATE TABLE `STUDENT` (
    st_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(40) UNIQUE NOT NULL ,
    full_name VARCHAR(100) DEFAULT NULL,
    year_of_studies int(2) DEFAULT NULL,
    current_avg_grade VARCHAR(40)  DEFAULT NULL,
    number_of_courses INT(2) DEFAULT NULL,
    user_id INT(10) NOT NULL,
    applications VARCHAR(1000) DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES `USER_`(us_id)
);
