package com.attendance.attendancemanagementsystem.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRecord {
    private int id;
    private String studentId;
    private LocalDate date;
    private String status;
    private LocalTime timeIn;

    public AttendanceRecord() {}

    public AttendanceRecord(String studentId, LocalDate date, String status, LocalTime timeIn) {
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.timeIn = timeIn;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalTime getTimeIn() { return timeIn; }
    public void setTimeIn(LocalTime timeIn) { this.timeIn = timeIn; }
}