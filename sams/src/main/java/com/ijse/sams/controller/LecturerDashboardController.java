package com.ijse.sams.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LecturerDashboardController {

    @FXML
    private AnchorPane AnchorPaneLecDash;

    @FXML
    private Pane PaneLecturerDash;

    @FXML
    private Button btnAttendanceManage;

    @FXML
    private Button btnClassScheduling;

    @FXML
    private Button btnLogout;

    @FXML
    private ToolBar toolBar1;

    @FXML
    private ToolBar toolBar2;

    @FXML
    private ToolBar toolBar3;

    private void loadUI(String fxml) {
        try {
            Pane newPane = FXMLLoader.load(getClass().getResource("/view/" + fxml));
            PaneLecturerDash.getChildren().clear();
            PaneLecturerDash.getChildren().add(newPane);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionAttendanceManage(ActionEvent event) {
            loadUI("attendance_marking.fxml");


    }

    @FXML
    void OnActionClassScheduling(ActionEvent event) {
        loadUI("lecturer_schedule_view.fxml");
    }

    @FXML
    void OnActionLogout(ActionEvent event) {

        System.exit(0);
    }

}
