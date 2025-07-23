package com.ijse.sams.controller;

import com.ijse.sams.entity.ClassSchedule;
import com.ijse.sams.entity.Course;
import com.ijse.sams.entity.Lecturer;
import com.ijse.sams.entity.Subject;
import com.ijse.sams.service.ClassScheduleService;
import com.ijse.sams.service.CourseService;
import com.ijse.sams.service.LecturerService;
import com.ijse.sams.service.SubjectService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClassScheduleController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneClasses;

    @FXML
    private Button btnAddClass;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelClass;

    @FXML
    private Button btnUpdateClass;

    @FXML
    private ComboBox<Course> cmbCourse;

    @FXML
    private ComboBox<Lecturer> cmbLecturer;

    @FXML
    private ComboBox<Subject> cmbSubject;

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
    private DatePicker dpDate;

    @FXML
    private TableView<ClassSchedule> tableClasses;

    private final ClassScheduleService scheduleService = new ClassScheduleService();
    private final CourseService courseService = new CourseService();
    private final SubjectService subjectService = new SubjectService();
    private final LecturerService lecturerService = new LecturerService();

    private ObservableList<ClassSchedule> scheduleList = FXCollections.observableArrayList();
    private ClassSchedule selectedSchedule;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colCId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCourse().getId()).asObject());
        colSubId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getSubject().getId()).asObject());
        colDate.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDate().toString()));
        colLecId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getLecturer().getId()).asObject());

        loadCourses();
        loadSubjects();
        loadLecturers();
        loadSchedules();

        tableClasses.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedSchedule = newSel;
                cmbCourse.setValue(newSel.getCourse());
                cmbSubject.setValue(newSel.getSubject());
                dpDate.setValue(newSel.getDate());
                cmbLecturer.setValue(newSel.getLecturer());
            }
        });
    }

    @FXML
    void onActionAddClass(ActionEvent event) {
        ClassSchedule schedule = new ClassSchedule();
        schedule.setCourse(cmbCourse.getValue());
        schedule.setSubject(cmbSubject.getValue());
        schedule.setDate(dpDate.getValue());
        schedule.setLecturer(cmbLecturer.getValue());

        scheduleService.addClassSchedule(schedule);
        loadSchedules();
        clearForm();
    }

    @FXML
    void onActionClear(ActionEvent event) {
        clearForm();
    }

    @FXML
    void onActionDelClass(ActionEvent event) {
        if (selectedSchedule != null) {

            scheduleService.deleteClassSchedule(selectedSchedule);
            loadSchedules();
            clearForm();
        }
    }

    @FXML
    void onActionUpdateClass(ActionEvent event) {
        if (selectedSchedule != null) {
            selectedSchedule.setCourse(cmbCourse.getValue());
            selectedSchedule.setSubject(cmbSubject.getValue());
            selectedSchedule.setDate(dpDate.getValue());
            selectedSchedule.setLecturer(cmbLecturer.getValue());

            scheduleService.updateClassSchedule(selectedSchedule);
            loadSchedules();
            clearForm();
        }
    }

    private void loadSubjects() {
        ObservableList<Subject> subjectList = FXCollections.observableArrayList(subjectService.getAllSubjects());
        cmbSubject.setItems(subjectList);
    }

    private void loadCourses() {
        ObservableList<Course> courseList = FXCollections.observableArrayList(courseService.getAllCourses());
        cmbCourse.setItems(courseList);
    }

    private void loadLecturers() {
        ObservableList<Lecturer> lecturerList = FXCollections.observableArrayList(lecturerService.getAllLecturers());
        cmbLecturer.setItems(lecturerList);
    }

    private void loadSchedules() {
        scheduleList.setAll(scheduleService.getAllClassSchedules());
        tableClasses.setItems(scheduleList);
    }



    private void clearForm() {

        cmbCourse.getSelectionModel().clearSelection();
        cmbSubject.getSelectionModel().clearSelection();
        cmbLecturer.getSelectionModel().clearSelection();
        dpDate.setValue(null);
        tableClasses.getSelectionModel().clearSelection();
        selectedSchedule = null;
    }

}
