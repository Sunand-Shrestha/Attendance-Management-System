package com.attendance.attendancemanagementsystem.controller;

import com.attendance.attendancemanagementsystem.dao.AttendanceDAO;
import com.attendance.attendancemanagementsystem.dao.StudentDAO;
import com.attendance.attendancemanagementsystem.model.AttendanceRecord;
import com.attendance.attendancemanagementsystem.model.Student;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private TextField studentIdField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colStudentId;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colEmail;
    @FXML private TableColumn<Student, String> colPhone;

    @FXML private ComboBox<String> attendanceStudentCombo;
    @FXML private ComboBox<String> statusCombo;
    @FXML private DatePicker datePicker;
    @FXML private TableView<AttendanceRecord> attendanceTable;
    @FXML private TableColumn<AttendanceRecord, String> colAttStudentId;
    @FXML private TableColumn<AttendanceRecord, LocalDate> colDate;
    @FXML private TableColumn<AttendanceRecord, String> colStatus;
    @FXML private TableColumn<AttendanceRecord, LocalTime> colTimeIn;

    private StudentDAO studentDAO = new StudentDAO();
    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        loadStudents();
        loadStudentCombo();
        statusCombo.getItems().addAll("Present", "Absent");
        datePicker.setValue(LocalDate.now());
    }

    private void setupTableColumns() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        colAttStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTimeIn.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
    }

    @FXML
    private void addStudent() {
        if (validateStudentInput()) {
            Student student = new Student(
                    studentIdField.getText(),
                    nameField.getText(),
                    emailField.getText(),
                    phoneField.getText()
            );

            if (studentDAO.addStudent(student)) {
                showAlert("Success", "Student added successfully!");
                clearStudentFields();
                loadStudents();
                loadStudentCombo();
            } else {
                showAlert("Error", "Failed to add student!");
            }
        }
    }

    @FXML
    private void updateStudent() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected != null && validateStudentInput()) {
            selected.setName(nameField.getText());
            selected.setEmail(emailField.getText());
            selected.setPhone(phoneField.getText());

            if (studentDAO.updateStudent(selected)) {
                showAlert("Success", "Student updated successfully!");
                clearStudentFields();
                loadStudents();
            } else {
                showAlert("Error", "Failed to update student!");
            }
        }
    }

    @FXML
    private void deleteStudent() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (studentDAO.deleteStudent(selected.getStudentId())) {
                showAlert("Success", "Student deleted successfully!");
                loadStudents();
                loadStudentCombo();
            } else {
                showAlert("Error", "Failed to delete student!");
            }
        }
    }

    @FXML
    private void markAttendance() {
        if (attendanceStudentCombo.getValue() != null && statusCombo.getValue() != null) {
            AttendanceRecord record = new AttendanceRecord(
                    attendanceStudentCombo.getValue(),
                    datePicker.getValue(),
                    statusCombo.getValue(),
                    statusCombo.getValue().equals("Present") ? LocalTime.now() : null
            );

            if (attendanceDAO.markAttendance(record)) {
                showAlert("Success", "Attendance marked successfully!");
                loadAttendance();
            } else {
                showAlert("Error", "Failed to mark attendance!");
            }
        }
    }

    @FXML
    private void loadAttendance() {
        if (datePicker.getValue() != null) {
            ObservableList<AttendanceRecord> records = attendanceDAO.getAttendanceByDate(datePicker.getValue());
            attendanceTable.setItems(records);
        }
    }

    @FXML
    private void onStudentSelect() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            studentIdField.setText(selected.getStudentId());
            nameField.setText(selected.getName());
            emailField.setText(selected.getEmail());
            phoneField.setText(selected.getPhone());
        }
    }

    private void loadStudents() {
        ObservableList<Student> students = studentDAO.getAllStudents();
        studentTable.setItems(students);
    }

    private void loadStudentCombo() {
        ObservableList<Student> students = studentDAO.getAllStudents();
        attendanceStudentCombo.getItems().clear();
        for (Student student : students) {
            attendanceStudentCombo.getItems().add(student.getStudentId());
        }
    }

    private boolean validateStudentInput() {
        return !studentIdField.getText().trim().isEmpty() &&
                !nameField.getText().trim().isEmpty();
    }

    private void clearStudentFields() {
        studentIdField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}