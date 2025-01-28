package org.example.restaurantmenuapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    // Simulate the user validation logic (you can replace this with a real database check)
    private boolean validateCredentials(String username, String password) {
        // Replace with actual validation logic
        return "admin".equals(username) && "112233".equals(password);
    }

    @FXML
    public void onLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validateCredentials(username, password)) {
            try {
                // Load the menu scene after successful login
                Stage stage = (Stage) statusLabel.getScene().getWindow();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }
}
