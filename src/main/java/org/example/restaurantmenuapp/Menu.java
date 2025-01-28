package org.example.restaurantmenuapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class Menu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage.setTitle("Restaurant Menu System");
        stage.setScene(new Scene(root, 400, 300)); // Adjust width and height as needed
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    }
