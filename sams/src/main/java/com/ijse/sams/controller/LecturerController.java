package com.ijse.sams.controller;

import com.ijse.sams.entity.Lecturer;
import com.ijse.sams.entity.Subject;
import com.ijse.sams.service.LecturerService;
import com.ijse.sams.service.SubjectService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LecturerController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneLecturer;

    @FXML
    private Button btnAddLecturer;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelLecturer;

    @FXML
    private Button btnUpdateLecturer;

    @FXML
    private ComboBox<Subject> cmbSubject;

    @FXML
    private TableColumn<Lecturer, String> colContact;

    @FXML
    private TableColumn<Lecturer, Integer> colLecId;

    @FXML
    private TableColumn<Lecturer, String> colName;

    @FXML
    private TableColumn<Lecturer, Integer> colSubId;

    @FXML
    private TableView<Lecturer> tableLecturers;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtLName;

    private final LecturerService lecturerService = new LecturerService();
    private final SubjectService subjectService = new SubjectService();

    private final ObservableList<Lecturer> lecList = FXCollections.observableArrayList();
    private Lecturer selectedLecturer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colLecId.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()).asObject());
        colName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        colContact.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getContact()));
        colSubId.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getSubject().getId()).asObject());

        cmbSubject.setItems(FXCollections.observableArrayList(subjectService.getAllSubjects()));

        loadLecturers();

        tableLecturers.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedLecturer = newVal;
                txtLName.setText(newVal.getName());
                txtContact.setText(newVal.getContact());
                cmbSubject.setValue(newVal.getSubject());
            }
        });
    }
    @FXML
    void onActionAddLecturer(ActionEvent event) {
        Lecturer lecturer = new Lecturer();
        lecturer.setName(txtLName.getText());
        lecturer.setContact(txtContact.getText());
        lecturer.setSubject(cmbSubject.getValue());

        lecturerService.addLecturer(lecturer);
        loadLecturers();
        clearForm();
    }

    @FXML
    void onActionClear(ActionEvent event) {
        clearForm();
    }

    @FXML
    void onActionDelLecturer(ActionEvent event) {
        if (selectedLecturer != null) {
            lecturerService.deleteLecturer(selectedLecturer);
            loadLecturers();
            clearForm();
        }
    }

    @FXML
    void onActionUpdateLecturer(ActionEvent event) {
        if (selectedLecturer != null) {
            selectedLecturer.setName(txtLName.getText());
            selectedLecturer.setContact(txtContact.getText());
            selectedLecturer.setSubject(cmbSubject.getValue());
        }

        lecturerService.updateLecturer(selectedLecturer);
        loadLecturers();
        clearForm();
    }

    private void loadLecturers() {
        lecList.setAll(lecturerService.getAllLecturers());
        tableLecturers.setItems(lecList);
    }

    private void clearForm() {
        txtLName.clear();
        txtContact.clear();
        cmbSubject.getSelectionModel().clearSelection();
        selectedLecturer = null;
        tableLecturers.getSelectionModel().clearSelection();

    }

}
