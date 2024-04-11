package com.example.decode_lab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
public class securityAuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button generatePasswordButton;

    @FXML
    private TextField generatePasswordResultTextField;

    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Label counterError_label;

    @FXML
    private Button enter_button;

    @FXML
    private PasswordField inputPassword_button;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String serverUrl = "http://localhost:8080"; // URL вашего Spring-сервера

    @FXML
    private AnchorPane mainPane_anchorPane;

    @FXML
    void initialize() {
        generatePasswordResultTextField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        inputPassword_button.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");

        enter_button.setOnAction(event -> {
            String password = inputPassword_button.getText();
            String response = checkPassword(password);
            counterError_label.setText(response);
            if (response.equals("Успешный вход!")) {
                enter_button.getScene().getWindow().hide();
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
            }
        });
        generatePasswordButton.setOnAction(event -> {
            String oneTimePassword = generateOneTimePasswordFromServer();
            generatePasswordResultTextField.setText(oneTimePassword);
            setValidPassword(oneTimePassword); // Добавьте эту строку для отправки пароля на сервер
        });
    }

    private String checkPassword(String password) {
        String checkUrl = serverUrl + "/checkPassword";
        return restTemplate.postForObject(checkUrl, password, String.class);
    }
    private String generateOneTimePasswordFromServer() {
        String generateUrl = serverUrl + "/generateOneTimePassword";
        return restTemplate.getForObject(generateUrl, String.class);
    }
    private void setValidPassword(String oneTimePassword) {
        String setValidPasswordUrl = serverUrl + "/setValidPassword";
        restTemplate.postForObject(setValidPasswordUrl, oneTimePassword, String.class);
    }
    private void setValidPasswordToNull() {
        String setValidPasswordToNullUrl = serverUrl + "/setValidPasswordToNull";
        restTemplate.postForObject(setValidPasswordToNullUrl, null, Void.class);
    }
}

