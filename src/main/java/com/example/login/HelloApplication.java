package com.example.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
//        stage.setTitle("Login Page");
//        stage.setScene(scene);
//        stage.show();


        primaryStage.setTitle("Login");

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Username:");
        TextField nameInput = new TextField();
        nameInput.setPromptText("username");

        Label passLabel = new Label("Password:");
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("password");

        Button loginButton = new Button("Log In");


        loginButton.setOnAction(e -> {
            // Replace this with your authentication logic
            String username = nameInput.getText();
            String password = passInput.getText();
            if (authenticate(username, password)) {
                System.out.println("Login successful");

                // You can navigate to another scene or perform other actions here
            } else {
                System.out.println("Login failed. Please try again.");
                IncorrectInformationsAlert incorrectInformationsAlert = new IncorrectInformationsAlert();
                Stage alertStage = new Stage();
                incorrectInformationsAlert.start(alertStage);
            }
        });

        root.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    // Dummy authentication method
    private boolean authenticate(String username, String password) {
        // Replace this with your actual authentication logic
        if (username.isBlank() == false && password.isBlank() == false) {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String verifyLogin = "SELECT COUNT(1) FROM users WHERE username = '" + username + "' AND password = '" + password + "';";

            try {
                Statement statement = connectDB.createStatement();
                ResultSet qrResults = statement.executeQuery(verifyLogin);

                while (qrResults.next()) {
                    if (qrResults.getInt(1) == 1){
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            IncorrectInformationsAlert incorrectInformationsAlert = new IncorrectInformationsAlert();
            Stage alertStage = new Stage();
            incorrectInformationsAlert.start(alertStage);
        }

        return false;
    }

    public static void main(String[] args) {
        launch();
    }
}