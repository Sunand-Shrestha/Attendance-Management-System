Xamp / phpmyadmin ma this do :
CREATE DATABASE attendance_system;
USE attendance_system;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(15)
);

CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id VARCHAR(20),
    date DATE NOT NULL,
    status ENUM('Present', 'Absent') NOT NULL,
    time_in TIME,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

-- Sample data (optional)
INSERT INTO students VALUES 
(1, 'STU001', 'John Doe', 'john@email.com', '1234567890'),
(2, 'STU002', 'Jane Smith', 'jane@email.com', '0987654321');
