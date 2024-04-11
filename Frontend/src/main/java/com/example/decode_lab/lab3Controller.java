package com.example.decode_lab;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class lab3Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backContents_buttons;

    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Button decryptText_button;

    @FXML
    private Button decrypt_button;

    @FXML
    private Label decrypt_label;

    @FXML
    private Button encryptText_button;

    @FXML
    private Button encrypt_button;

    @FXML
    private Label inputInvite_label;

    @FXML
    private TextField inputText_textField;

    @FXML
    private AnchorPane mainPane_anchorPane;

    @FXML
    private TextField outputText_textField;

    @FXML
    private Label resultField_label;

    @FXML
    private TextField secretKey_textField;
    private static final String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final int alphabetLength = russianAlphabet.length();

    @FXML
    void initialize() {
        // кнопка загрузки .txt
        FileChooser fileChooser = new FileChooser();
        encrypt_button.setOnAction(event -> {

            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    inputText_textField.setText(content.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String inputText = inputText_textField.getText();
            String key = secretKey_textField.getText();
            String encryptedText = encryptVigenere(inputText, key);
            outputText_textField.setText(encryptedText);
        });
        //кнопка дешифровки (файлом)
        decrypt_button.setOnAction(event -> {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.dat"));

            File selectedFile = fileChooser.showOpenDialog(null);


            if (selectedFile != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    inputText_textField.setText(content.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String encryptedText = inputText_textField.getText(); // Получаем текст для дешифровки
            String key = secretKey_textField.getText();
            String decryptedText = decryptVigenere(encryptedText, key); // Дешифруем текст

            // Выводим дешифрованный текст в поле вывода
            outputText_textField.setText(decryptedText);
        });
        // задаём цвет
        inputText_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        outputText_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        secretKey_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        // Изначально делаем кнопки неактивными
        encryptText_button.setDisable(true);
        decryptText_button.setDisable(true);
        encrypt_button.setDisable(true);
        decrypt_button.setDisable(true);
        // при нажатии с пумощью фкнции vigenereEncrypt шифруем
        encryptText_button.setOnAction(event -> {
            String inputText = inputText_textField.getText();
            String key = secretKey_textField.getText();
            String encryptedText = encryptVigenere(inputText, key);
            outputText_textField.setText(encryptedText);

        });
        // проверка, для активации кнопок
        secretKey_textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Проверяем длину введенного текста
            boolean isKeyValid = newValue.length() >= 5;
            boolean isTextValid = inputText_textField.getText().length() >= 5;
            // Активируем кнопки только если оба условия выполнены
            encryptText_button.setDisable(!(isKeyValid && isTextValid));
            decryptText_button.setDisable(!(isKeyValid && isTextValid));
            encrypt_button.setDisable(!(isKeyValid));
            decrypt_button.setDisable(!(isKeyValid));
        });
        // Добавляем слушатель изменений в поле ввода текста
        inputText_textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Проверяем длину введенного текста
            boolean isKeyValid = secretKey_textField.getText().length() >= 5;
            boolean isTextValid = newValue.length() >= 5;
            // Активируем кнопки только если оба условия выполнены
            encryptText_button.setDisable(!(isKeyValid && isTextValid));
            decryptText_button.setDisable(!(isKeyValid && isTextValid));
            encrypt_button.setDisable(!(isKeyValid));
            decrypt_button.setDisable(!(isKeyValid));
        });
        //Дешифрование
        decryptText_button.setOnAction(event -> {
            String encryptedText = inputText_textField.getText(); // Получаем текст для дешифровки
            String key = secretKey_textField.getText();
            String decryptedText = decryptVigenere(encryptedText, key); // Дешифруем текст

            // Выводим дешифрованный текст в поле вывода
            outputText_textField.setText(decryptedText);
        });
        // кнопка возврата к оглавлению
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

    }


    public static String encryptVigenere(String plainText, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < plainText.length(); i++) {
            char plainChar = plainText.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLowerCase(plainChar)) {
                int plainIndex = russianAlphabet.indexOf(plainChar);
                int keyIndex = russianAlphabet.indexOf(keyChar);
                int shiftedIndex = (plainIndex + keyIndex) % alphabetLength;
                encryptedText.append(russianAlphabet.charAt(shiftedIndex));
            } else {
                encryptedText.append(plainChar);
            }
        }

        return encryptedText.toString();
    }

    public static String decryptVigenere(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedChar = encryptedText.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLowerCase(encryptedChar)) {
                int encryptedIndex = russianAlphabet.indexOf(encryptedChar);
                int keyIndex = russianAlphabet.indexOf(keyChar);
                int shiftedIndex = (encryptedIndex - keyIndex + alphabetLength) % alphabetLength;
                decryptedText.append(russianAlphabet.charAt(shiftedIndex));
            } else {
                decryptedText.append(encryptedChar);
            }
        }

        return decryptedText.toString();
    }
}