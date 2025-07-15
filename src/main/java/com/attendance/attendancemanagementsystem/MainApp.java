package com.attendance.attendancemanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(getClass().getResource("/com/attendance/attendancemanagementsystem/main.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/attendance/attendancemanagementsystem/main.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);

        primaryStage.setTitle("Attendance Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
