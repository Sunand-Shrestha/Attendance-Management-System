package com.attendance.attendancemanagementsystem.dao;


import com.attendance.attendancemanagementsystem.database.DatabaseConnection;
import com.attendance.attendancemanagementsystem.model.AttendanceRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class AttendanceDAO {

    public boolean markAttendance(AttendanceRecord record) {
        String sql = "INSERT INTO attendance (student_id, date, status, time_in) VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE status=?, time_in=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, record.getStudentId());
            pstmt.setDate(2, Date.valueOf(record.getDate()));
            pstmt.setString(3, record.getStatus());
            pstmt.setTime(4, record.getTimeIn() != null ? Time.valueOf(record.getTimeIn()) : null);
            pstmt.setString(5, record.getStatus());
            pstmt.setTime(6, record.getTimeIn() != null ? Time.valueOf(record.getTimeIn()) : null);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<AttendanceRecord> getAttendanceByDate(LocalDate date) {
        ObservableList<AttendanceRecord> records = FXCollections.observableArrayList();
        String sql = "SELECT a.*, s.name FROM attendance a JOIN students s ON a.student_id = s.student_id WHERE a.date = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                AttendanceRecord record = new AttendanceRecord();
                record.setId(rs.getInt("id"));
                record.setStudentId(rs.getString("student_id"));
                record.setDate(rs.getDate("date").toLocalDate());
                record.setStatus(rs.getString("status"));
                Time timeIn = rs.getTime("time_in");
                if (timeIn != null) {
                    record.setTimeIn(timeIn.toLocalTime());
                }
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}