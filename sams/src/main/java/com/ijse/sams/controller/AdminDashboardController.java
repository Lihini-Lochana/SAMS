package com.ijse.sams.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AdminDashboardController {
    public AnchorPane AnchorPaneAdminDash;
    public Pane PaneAdminDash;
    public ToolBar toolBar1;
    public Button btnCourseManage;
    public ToolBar toolBar2;
    public Button btnStudentManage;
    public ToolBar toolBar3;
    public Button btnLecManage;
    public ToolBar toolBar4;
    public Button btnClassScheduling;
    public ToolBar toolBar6;
    public Button btnLogout;
    public ToolBar toolBar5;
    public Button btnShowReports;

    private void loadUI(String fxml) {
        try {
            Pane newPane = FXMLLoader.load(getClass().getResource("/view/" + fxml));
            PaneAdminDash.getChildren().clear();
            PaneAdminDash.getChildren().add(newPane);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void OnActionCourseManage(ActionEvent actionEvent) {
        loadUI("course_management.fxml");
    }

    public void OnActionStudentManage(ActionEvent actionEvent) {
        loadUI("student_management.fxml");
    }

    public void OnActionLecManage(ActionEvent actionEvent) {
        loadUI("lecturer_management.fxml");
    }

    public void OnActionClassScheduling(ActionEvent actionEvent) {
        loadUI("class_scheduling.fxml");
    }

    public void OnActionLogout(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void OnActionShowReports(ActionEvent actionEvent) {
        loadUI("reports.fxml");
    }
}
