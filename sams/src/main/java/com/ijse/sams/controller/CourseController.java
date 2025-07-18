package com.ijse.sams.controller;

import com.ijse.sams.entity.Course;
import com.ijse.sams.service.CourseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    public AnchorPane AnchorPaneCourse;
    public TextField txtCname;
    public TextField txtDuration;
    public Button btnAddCourse;
    public Button btnUpdateCourse;
    public Button btnDelCourse;
    public Button btnClear;
    public TableView<Course> tableCourses;
    public TableColumn<Course, Integer> colId;
    public TableColumn<Course, String> colName;
    public TableColumn<Course, Integer> colDuration;

    private CourseService courseService = new CourseService();
    private ObservableList<Course> courseList = FXCollections.observableArrayList();

    private Course selectedCourse;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId()).asObject());
        colName.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        colDuration.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getDuration()).asObject());

        loadCourses();

        tableCourses.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedCourse = newSelection;
                txtCname.setText(selectedCourse.getName());
                txtDuration.setText(String.valueOf(selectedCourse.getDuration()));
            }
        });
    }

    public void onActionAddCourse(ActionEvent actionEvent) {
        String name = txtCname.getText();
        int duration = Integer.parseInt(txtDuration.getText());

        courseService.addCourse(name, duration);
        loadCourses();
        clearForm();
    }

    public void onActionUpdateCourse(ActionEvent actionEvent) {
        if (selectedCourse != null) {
            selectedCourse.setName(txtCname.getText());
            selectedCourse.setDuration(Integer.parseInt(txtDuration.getText()));
            courseService.updateCourse(selectedCourse);
            loadCourses();
            clearForm();
        }
    }

    public void onActionDelCourse(ActionEvent actionEvent) {
        if (selectedCourse != null) {
            courseService.deleteCourse(selectedCourse);
            loadCourses();
            clearForm();
        }
    }

    public void onActionClear(ActionEvent actionEvent) {
        clearForm();
    }

    private void loadCourses() {
        courseList.setAll(courseService.getAllCourses());
        tableCourses.setItems(courseList);
    }

    private void clearForm() {
        txtCname.clear();
        txtDuration.clear();
        selectedCourse = null;
        tableCourses.getSelectionModel().clearSelection();
    }
}
