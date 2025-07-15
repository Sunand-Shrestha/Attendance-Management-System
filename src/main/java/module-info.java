module com.attendance.attendancemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.attendance.attendancemanagementsystem to javafx.fxml;
    opens com.attendance.attendancemanagementsystem.controller to javafx.fxml;
    opens com.attendance.attendancemanagementsystem.model to javafx.base;

    exports com.attendance.attendancemanagementsystem;
    exports com.attendance.attendancemanagementsystem.controller;
    exports com.attendance.attendancemanagementsystem.model;
}