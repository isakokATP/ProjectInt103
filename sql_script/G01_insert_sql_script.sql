-- Database
CREATE DATABASE IF NOT EXISTS int103;

-- Use
USE int103;

create user 'int103'@'localhost' identified by 'int103';

grant all privileges on int103.* to 'int103'@'localhost';

-- Drop
DROP TABLE IF EXISTS registration;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS courses;

-- Table
CREATE TABLE `int103`.`students` (
  `student_id` BIGINT NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `student_id_UNIQUE` (`student_id` ASC) VISIBLE);
  
  CREATE TABLE `int103`.`courses` (
  `course_id` VARCHAR(6) NOT NULL,
  `course_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`course_id`));

CREATE TABLE registration (
  registration_id INT AUTO_INCREMENT PRIMARY KEY,
  student_id BIGINT,
  course_id VARCHAR(6),
  FOREIGN KEY (student_id) REFERENCES students(student_id),
  FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

-- ------------ Insert ------------ --

-- Inserting data into the 'students' table
INSERT INTO students (student_id, first_name, last_name, email)
VALUES ('65130500001', 'John', 'Doe', 'johndoe@example.com'),
       ('65130500002', 'Jane', 'Smith', 'janesmith@example.com'),
       ('65130500003', 'Michael', 'Johnson', 'michaeljohnson@example.com'),
       ('65130500004', 'Emily', 'Brown', 'emilybrown@example.com'),
       ('65130500005', 'David', 'Taylor', 'davidtaylor@example.com'),
       ('65130500006', 'Olivia', 'Martinez', 'oliviamartinez@example.com'),
       ('65130500007', 'William', 'Anderson', 'williamanderson@example.com'),
       ('65130500008', 'Sophia', 'Thomas', 'sophiathomas@example.com'),
       ('65130500009', 'James', 'Wilson', 'jameswilson@example.com'),
       ('65130500010', 'Emma', 'Moore', 'emmamoore@example.com');

-- Inserting data into the 'courses' table
INSERT INTO courses (course_id, course_name)
VALUES ('INT103', 'Advanced Programming'),
	   ('INT201', 'Web-based Client-Side Programming I'),
       ('INT202', 'Web-based Server-Side Programming I'),
       ('INT203', 'Web-based Client-Side Programming II'),
       ('INT204', 'Web-based Server-Side Programming II'),
       ('INT206', 'Advanced Database'),
       ('INT208', 'Network II'),
       ('INT209', 'Development and Operations (DevOps)'),
       ('INT210', 'Architecture, Integration and Deployment'),
       ('INT221', 'Integrated IT Project I'),
       ('LNG308', 'Technical Report Writing'),
       ('LNG202', 'Basic Reading for Science and Technology');

-- Inserting data into the 'registration' table
INSERT INTO registration (student_id, course_id)
VALUES ('65130500001', 'INT103'),
       ('65130500001', 'INT202'),
       ('65130500001', 'INT203'),
       ('65130500001', 'INT206'),
       ('65130500002', 'INT204'),
       ('65130500002', 'INT206'),
       ('65130500002', 'INT208'),
       ('65130500003', 'INT203'),
       ('65130500003', 'INT204'),
       ('65130500003', 'INT210');
       

select * from students;
select * from courses;
select * from registration;