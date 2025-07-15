Xamp / phpmyadmin ma this do :
CREATE DATABASE attendance_system;<br>
USE attendance_system;<br>

CREATE TABLE students (<br>
    id INT PRIMARY KEY AUTO_INCREMENT,<br>
    student_id VARCHAR(20) UNIQUE NOT NULL,<br>
    name VARCHAR(100) NOT NULL,<br>
    email VARCHAR(100),<br>
    phone VARCHAR(15)<br>
);

CREATE TABLE attendance (<br>
    id INT PRIMARY KEY AUTO_INCREMENT,<br>
    student_id VARCHAR(20),<br>
    date DATE NOT NULL,<br>
    status ENUM('Present', 'Absent') NOT NULL,<br>
    time_in TIME,<br>
    FOREIGN KEY (student_id) REFERENCES students(student_id)<br>
);<br>


-- Sample data (optional)
INSERT INTO students VALUES 
(1, 'STU001', 'John Doe', 'john@email.com', '1234567890'),
(2, 'STU002', 'Jane Smith', 'jane@email.com', '0987654321');
