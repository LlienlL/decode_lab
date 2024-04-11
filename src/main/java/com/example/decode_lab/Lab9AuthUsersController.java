package com.example.decode_lab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.decode_lab.SQL.DatabaseManager;
import com.example.decode_lab.SQL.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Lab9AuthUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField InputLogin_textField;

    @FXML
    private PasswordField InputPassword_passwordField;

    @FXML
    private Button Register_button;

    @FXML
    private Button backContents_buttons;

    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Label counterError_label;

    @FXML
    private Button enter_button;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/decode_lab_base";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123";


    @FXML
    private AnchorPane mainPane_anchorPane;

    @FXML
    void initialize() {
        backContents_buttons.setOnAction(event -> {
            backContents_buttons.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("TaskSelectionWindow.fxml")));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Susu_422_decoder");
            stage.show();
        });
        InputLogin_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        InputPassword_passwordField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        Register_button.setOnAction(event -> {
            backContents_buttons.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("lab9RegistUsers.fxml")));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Susu_422_decoder");
            stage.show();
        });
        enter_button.setOnAction(event -> {
            login(InputLogin_textField, InputPassword_passwordField);
        });
    }

    private DatabaseManager databaseManager = new DatabaseManager();

    public void login(TextField usernameField, PasswordField passwordField) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = databaseManager.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            openMainForm(user.getRole());
        } else {
            counterError_label.setText("Неверный логин или пароль.");
        }
    }

    private void openMainForm(int role) {
        // В зависимости от роли открываем соответствующую форму
        switch (role) {
            case 1:
                openFormForRole1();
                break;
            case 2:
                openFormForRole2();
                break;
            case 3:
                openFormForRole3();
                break;
            default:
                System.out.println("Неизвестная роль");
        }
    }

    private void openFormForRole1() {
        // Открываем форму для роли 1
        enter_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("WindowRole1.fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Susu_422_decoder");
        stage.show();
    }

    private void openFormForRole2() {
        // Открываем форму для роли 2
        enter_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("WindowRole2.fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Susu_422_decoder");
        stage.show();
    }

    private void openFormForRole3() {
        // Открываем форму для роли 3
        enter_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("WindowRole3.fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Susu_422_decoder");
        stage.show();
    }

}