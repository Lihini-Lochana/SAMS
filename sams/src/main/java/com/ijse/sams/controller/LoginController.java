package com.ijse.sams.controller;

import com.ijse.sams.dao.UserDAO;
import com.ijse.sams.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
    public AnchorPane AnchorPane1_login;
    public TextField txtUname;
    public Button btnLogin;
    public PasswordField txtPassword;

    private final UserDAO userDAO = new UserDAO();

    public void onActionLogin(ActionEvent actionEvent) {
        String username = txtUname.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            displayAlert("Please fill both username and password.");
            return;
        }

        User user = userDAO.checkLogin(username, password);

        if (user == null) {
            displayAlert("Wrong username or password.");
            return;
        }
        displayAlert("Welcome " + user.getRole());



            try {
                String fxml = user.getRole().equals("admin") ? "/admin_dashboard.fxml" : "/lecturer_dashboard.fxml";

                Parent root = FXMLLoader.load(getClass().getResource(fxml));
                Stage stage = (Stage) AnchorPane1_login.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                e.printStackTrace();
                displayAlert("Error loading dashboard!!");
            }




    }

    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
