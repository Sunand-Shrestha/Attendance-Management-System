module com.attendance.attendancemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.attendance.attendancemanagementsystem to javafx.fxml;
    exports com.attendance.attendancemanagementsystem;
}