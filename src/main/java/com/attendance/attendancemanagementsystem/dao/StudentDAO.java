package com.attendance.attendancemanagementsystem.dao;

import com.attendance.attendancemanagementsystem.database.DatabaseConnection;
import com.attendance.attendancemanagementsystem.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class StudentDAO {

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, name, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhone());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<Student> getAllStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, email=?, phone=? WHERE student_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPhone());
            pstmt.setString(4, student.getStudentId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM students WHERE student_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}