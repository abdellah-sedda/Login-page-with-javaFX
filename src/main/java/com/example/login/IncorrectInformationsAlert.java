package com.example.login;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IncorrectInformationsAlert extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Alert");

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Les informations sont incorrect:");

        Button agreeButton = new Button("OK !");
        agreeButton.setOnAction(e -> {
            // Close this window
            Stage stage = (Stage) agreeButton.getScene().getWindow();
            stage.close();
        });

        root.getChildren().addAll(nameLabel, agreeButton);

        Scene scene = new Scene(root, 300, 170);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
