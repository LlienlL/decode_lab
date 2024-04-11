package com.example.decode_lab;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class lab10HammingDistanceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backContents_buttons;

    @FXML
    private CheckBox ck1;

    @FXML
    private CheckBox ck2;

    @FXML
    private CheckBox ck3;

    @FXML
    private CheckBox ck4;

    @FXML
    private CheckBox ck5;

    @FXML
    private CheckBox ck6;

    @FXML
    private CheckBox ck7;

    @FXML
    private CheckBox ck8;
    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Label decrypt_label;

    @FXML
    private Label infoLabel;

    @FXML
    private AnchorPane mainPane_anchorPane;

    @FXML
    private Button resultButton;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;

    @FXML
    private TextField text4;

    @FXML
    private TextField text5;

    @FXML
    private TextField text6;

    @FXML
    private TextField text7;

    @FXML
    private TextField text8;

    private List<CheckBox> checkBoxes;
    private List<TextField> textFields;

    @FXML
    void initialize() {
        text1.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        text2.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        text3.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        text4.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        text5.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        text6.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        text7.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        text8.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
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

        checkBoxes = List.of(ck1, ck2, ck3, ck4, ck5, ck6, ck7, ck8);
        textFields = List.of(text1, text2, text3, text4, text5, text6, text7, text8);

        configureCheckBoxes();
        resultButton.setOnAction(event -> calculateHammingDistance());
    }

    private void configureCheckBoxes() {
        for (int i = 0; i < checkBoxes.size(); i++) {
            CheckBox checkBox = checkBoxes.get(i);
            TextField textField = textFields.get(i);

            configureCheckBox(checkBox, textField);
        }
    }

    private void configureCheckBox(CheckBox checkBox, TextField textField) {
        checkBox.setOnAction(event -> {
            boolean disable = !checkBox.isSelected();
            textField.setDisable(disable);
        });
    }

    private void calculateHammingDistance() {
        List<String> activeStrings = getActiveStrings();

        // Вместо calculateMinHammingDistance вызываем calculateBinaryDifference
        int totalDifference = calculateBinaryDifference(activeStrings.toArray(new String[0]));

        // Обновляем resultLabel с результатом
        resultLabel.setText(String.valueOf(totalDifference));
    }

    private int calculateBinaryDifference(String... strings) {
        // Перебираем все возможные пары строк
        int totalDifference = 0;

        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                String s1 = strings[i];
                String s2 = strings[j];

                // Переводим строки в двоичный вид
                String binaryS1 = stringToBinary(s1);
                String binaryS2 = stringToBinary(s2);

                // Подсчитываем различающиеся биты
                totalDifference += countDifferentBits(binaryS1, binaryS2);
            }
        }

        return totalDifference;
    }

    private String stringToBinary(String s) {
        StringBuilder binaryStringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            binaryStringBuilder.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        return binaryStringBuilder.toString();
    }

    private int countDifferentBits(String s1, String s2) {
        int difference = 0;

        // Подсчитываем различающиеся биты
        int minLength = Math.min(s1.length(), s2.length());
        for (int k = 0; k < minLength; k++) {
            if (s1.charAt(k) != s2.charAt(k)) {
                difference++;
            }
        }

        // Добавим разницу в длине строк
        int maxLength = Math.max(s1.length(), s2.length());
        difference += Math.abs(s1.length() - s2.length());

        return difference;
    }

    private List<String> getActiveStrings() {
        List<String> activeStrings = new ArrayList<>();

        for (int i = 0; i < checkBoxes.size(); i++) {
            CheckBox checkBox = checkBoxes.get(i);
            TextField textField = textFields.get(i);

            if (!textField.isDisabled()) {
                activeStrings.add(textField.getText());
            }
        }

        return activeStrings;
    }

    private int calculateMinHammingDistance(String... strings) {
        int minHammingDistance = Integer.MAX_VALUE;

        // Перебираем все возможные пары строк
        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                int hammingDistance = calculateHammingDistance(strings[i], strings[j]);
                minHammingDistance = Math.min(minHammingDistance, hammingDistance);
            }
        }

        return minHammingDistance;
    }

    private int calculateHammingDistance(String s1, String s2) {
        int hammingDistance = 0;

        // Подсчитываем различающиеся биты
        int minLength = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                hammingDistance++;
            }
        }

        // Добавим разницу в длине строк
        int maxLength = Math.max(s1.length(), s2.length());
        hammingDistance += maxLength - minLength;

        return hammingDistance;
    }
}
