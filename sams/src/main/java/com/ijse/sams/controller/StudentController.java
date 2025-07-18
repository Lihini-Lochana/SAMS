package com.ijse.sams.controller;

import com.ijse.sams.entity.Course;
import com.ijse.sams.entity.Student;
import com.ijse.sams.service.CourseService;
import com.ijse.sams.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    public TextField txtSName;
    @FXML
    private AnchorPane AnchorPaneStudent;

    @FXML
    private Button btnAddStudent;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelStudent;

    @FXML
    private Button btnUpdateStudent;

    @FXML
    private ComboBox<Course> cmbCourse;

    @FXML
    private TableColumn<Student, Integer> colCId;

    @FXML
    private TableColumn<Student, String> colContact;

    @FXML
    private TableColumn<Student, String> colId;

    @FXML
    private TableColumn<Student, String> colName;

    @FXML
    private TableColumn<Student, String> colRegNo;

    @FXML
    private TableView<Student> tableStudents;

    @FXML
    private TextField txtCId;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtRegNo;


    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private Student selectedStudent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        colRegNo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRegNo()));
        colContact.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getContact()));
        colCId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCourse().getId()).asObject());

        loadCourses();
        loadStudents();

        tableStudents.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedStudent = newSel;
                txtSName.setText(newSel.getName());
                txtRegNo.setText(newSel.getRegNo());
                txtContact.setText(newSel.getContact());
                cmbCourse.setValue(newSel.getCourse());
            }
        });
    }



    @FXML
    void onActionAddStudent(ActionEvent event) {
        Student student = new Student();
        student.setName(txtSName.getText());
        student.setRegNo(txtRegNo.getText());
        student.setContact(txtContact.getText());
        student.setCourse(cmbCourse.getValue());
        studentService.addStudent(student);
        loadStudents();
        clearForm();

    }

    @FXML
    void onActionClear(ActionEvent event) {
        clearForm();
    }

    @FXML
    void onActionDelStudent(ActionEvent event) {
        if (selectedStudent != null) {
            studentService.deleteStudent(selectedStudent);
            loadStudents();
            clearForm();
        }

    }

    @FXML
    void onActionUpdateStudent(ActionEvent event) {
        if (selectedStudent != null) {
            selectedStudent.setName(txtSName.getText());
            selectedStudent.setRegNo(txtRegNo.getText());
            selectedStudent.setContact(txtContact.getText());
            selectedStudent.setCourse(cmbCourse.getValue());

            studentService.updateStudent(selectedStudent);
            loadStudents();
            clearForm();
        }
    }

    private void loadStudents() {
        studentList.setAll(studentService.getAllStudents());
        tableStudents.setItems(studentList);
    }

    private void loadCourses() {
        ObservableList<Course> courseList = FXCollections.observableArrayList(courseService.getAllCourses());
        cmbCourse.setItems(courseList);
    }

    private void clearForm() {
        txtSName.clear();
        txtRegNo.clear();
        txtContact.clear();
        cmbCourse.getSelectionModel().clearSelection();
        tableStudents.getSelectionModel().clearSelection();
        selectedStudent = null;
    }

}
