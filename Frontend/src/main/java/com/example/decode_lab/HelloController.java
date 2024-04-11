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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label counterError_label;

    @FXML
    private Button enter_button;

    @FXML
    private PasswordField inputPassword_button;

    @FXML
    private AnchorPane mainPane_anchorPane;
    private int numberOfErrors = 0;

    @FXML
    void initialize() {
        inputPassword_button.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");

        enter_button.setOnAction(actionEvent -> {
            String enteredPassword = inputPassword_button.getText();
            if ("123".equals(enteredPassword)) {
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
                stage.show();
            } else {
                numberOfErrors++;
                counterError_label.setText("Количество попыток до закрытия: " + (3 - numberOfErrors));

                if (numberOfErrors > 2) {
                    System.out.println("Превышено количество попыток. Завершение программы.");
                    System.exit(0);
                }
            }
        });
    }

}
