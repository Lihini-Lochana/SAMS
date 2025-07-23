package com.ijse.sams.controller;

import com.ijse.sams.entity.ClassSchedule;
import com.ijse.sams.entity.Course;
import com.ijse.sams.service.ClassScheduleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerScheduleViewController implements Initializable {

    @FXML
    private TableColumn<ClassSchedule, Integer> colCId;

    @FXML
    private TableColumn<ClassSchedule, String> colDate;

    @FXML
    private TableColumn<ClassSchedule, Integer> colId;

    @FXML
    private TableColumn<ClassSchedule, Integer> colLecId;

    @FXML
    private TableColumn<ClassSchedule, Integer> colSubId;

    @FXML
    private TableView<ClassSchedule> tableClasses;

    private final ClassScheduleService scheduleService = new ClassScheduleService();
    private ObservableList<ClassSchedule> scheduleList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colCId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCourse().getId()).asObject());
        colSubId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getSubject().getId()).asObject());
        colDate.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDate().toString()));
        colLecId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getLecturer().getId()).asObject());

        loadSchedules();
    }

    private void loadSchedules() {
        scheduleList.setAll(scheduleService.getAllClassSchedules());
        tableClasses.setItems(scheduleList);
    }



}
