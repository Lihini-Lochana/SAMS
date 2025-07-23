package com.ijse.sams.controller;

import com.ijse.sams.dao.AttendanceDAO;
import com.ijse.sams.dao.ClassScheduleDAO;
import com.ijse.sams.dao.StudentDAO;
import com.ijse.sams.entity.Attendance;
import com.ijse.sams.entity.ClassSchedule;
import com.ijse.sams.entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceMarkingController implements Initializable {
    public AnchorPane AnchorPaneAttendanceMark;
    public Button btnSaveAttendance;

    @FXML
    private ComboBox<ClassSchedule> cmbClass;

    @FXML
    private TableView<AttendanceRow> tableAttendance;

    @FXML
    private TableColumn<AttendanceRow, Long> colAId;

    @FXML
    private TableColumn<AttendanceRow, String> colSName;

    @FXML
    private TableColumn<AttendanceRow, String> colStatus;

    private final AttendanceDAO attendanceDAO = new AttendanceDAO();
    private final StudentDAO studentDAO = new StudentDAO();
    private final ClassScheduleDAO classScheduleDAO = new ClassScheduleDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbClass.setItems(FXCollections.observableArrayList(classScheduleDAO.getAllClassSchedules()));
        cmbClass.setOnAction(e -> loadAttendanceTable());

        colAId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colStatus.setCellFactory(column -> new TableCell<>() {
            private final ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("Present", "Absent"));

            {
                comboBox.setEditable(false);
                comboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
                    AttendanceRow row = getTableView().getItems().get(getIndex());
                    if (row != null) row.setStatus(newVal);
                });
            }

            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    comboBox.setValue(status);
                    setGraphic(comboBox);
                }
            }
        });
    }

    private void loadAttendanceTable() {
        ClassSchedule selectedClass = cmbClass.getSelectionModel().getSelectedItem();
        if (selectedClass == null) return;

        int courseId = selectedClass.getCourse().getId();
        List<Student> studentList = studentDAO.getStudentsByCourse(courseId);

        ObservableList<AttendanceRow> attendanceRows = FXCollections.observableArrayList();

        for (Student student : studentList) {
            attendanceRows.add(new AttendanceRow(null, student.getName(), "Absent", student));
        }

        tableAttendance.setItems(attendanceRows);
    }

    @FXML
    public void OnActionSaveAttendance(ActionEvent actionEvent) {
        ClassSchedule selectedClass = cmbClass.getSelectionModel().getSelectedItem();
        if (selectedClass == null) {
            showAlert("Please select a class schedule before saving attendance.");
            return;
        }

        LocalDate today = LocalDate.now();
        List<AttendanceRow> rows = tableAttendance.getItems();

        for (AttendanceRow row : rows) {
            Attendance attendance = new Attendance();
            attendance.setDate(today);
            attendance.setStatus(row.getStatus());
            attendance.setStudent(row.getStudent());
            attendance.setClassSchedule(selectedClass);
            attendanceDAO.saveAttendance(attendance);
        }

        showAlert("Attendance saved successfully.");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attendance");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class AttendanceRow {
        private Long attendanceId;
        private String studentName;
        private String status;
        private Student student;

        public AttendanceRow(Long attendanceId, String studentName, String status, Student student) {
            this.attendanceId = attendanceId;
            this.studentName = studentName;
            this.status = status;
            this.student = student;
        }

        public Long getAttendanceId() {
            return attendanceId;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Student getStudent() {
            return student;
        }
    }
}
