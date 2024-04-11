package com.example.decode_lab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Lab9RegistUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField InputLogin_textField;

    @FXML
    private PasswordField InputPassword_passwordField;

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
        enter_button.setOnAction(e -> registerUser(InputLogin_textField.getText(), InputPassword_passwordField.getText(), 1));

    }

    private void registerUser(String username, String password, int role) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setInt(3, role);
                preparedStatement.executeUpdate();
            }
            counterError_label.setText("Пользователь зарегистрирован успешно!");
        } catch (Exception e) {
            e.printStackTrace();
            counterError_label.setText("Ошибка при регистрации пользователя.");
        }
    }

}