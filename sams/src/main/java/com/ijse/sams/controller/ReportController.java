package com.ijse.sams.controller;

import com.ijse.sams.entity.Attendance;
import com.ijse.sams.service.AttendanceReportService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.List;

public class ReportController {

    @FXML
    private AnchorPane AnchorPaneReports;

    @FXML
    private Button btnCId;

    @FXML
    private Button btnDate;

    @FXML
    private Button btnSId;

    @FXML
    private TableColumn<Attendance, String> colCourse;

    @FXML
    private TableColumn<Attendance, String> colDate;

    @FXML
    private TableColumn<Attendance, String> colStatus;

    @FXML
    private TableColumn<Attendance, String> colStu;

    @FXML
    private DatePicker dpFrom;

    @FXML
    private DatePicker dpTo;

    @FXML
    private TableView<Attendance> tblReports;

    @FXML
    private TextField txtCId;

    @FXML
    private TextField txtSId;

    private final AttendanceReportService service = new AttendanceReportService();

    @FXML
    public void initialize() {
        colStu.setCellValueFactory(cell ->
                javafx.beans.binding.Bindings.createStringBinding(() ->
                        cell.getValue().getStudent().getName()));
        colCourse.setCellValueFactory(cell ->
                javafx.beans.binding.Bindings.createStringBinding(() ->
                        cell.getValue().getClassSchedule().getCourse().getName()));
        colDate.setCellValueFactory(cell ->
                javafx.beans.binding.Bindings.createStringBinding(() ->
                        cell.getValue().getDate().toString()));
        colStatus.setCellValueFactory(cell ->
                javafx.beans.binding.Bindings.createStringBinding(() ->
                        cell.getValue().getStatus()));
    }


    @FXML
    void OnViewByCourse(ActionEvent event) {
        int id = Integer.parseInt(txtCId.getText());
        List<Attendance> list = service.getAttendanceByCourse(id);
        tblReports.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    void OnViewByDate(ActionEvent event) {
        LocalDate from = dpFrom.getValue();
        LocalDate to = dpTo.getValue();
        List<Attendance> list = service.getAttendanceByDateRange(from, to);
        tblReports.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    void OnViewByStudent(ActionEvent event) {
        int id = Integer.parseInt(txtSId.getText());
        List<Attendance> list = service.getAttendanceByStudent(id);
        tblReports.setItems(FXCollections.observableArrayList(list));
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
