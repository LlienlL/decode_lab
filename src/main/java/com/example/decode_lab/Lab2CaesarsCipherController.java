package com.example.decode_lab;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Lab2CaesarsCipherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button decrypt_button;

    @FXML
    private Label decrypt_label;

    @FXML
    private Button encrypt_button;

    @FXML
    private Label inputInvite_label;

    @FXML
    private AnchorPane mainPane_anchorPane;
    @FXML
    private Button backContents_buttons;

    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Label resultField_label;

    @FXML
    private TextField secretKey_textField;
    FileChooser fileChooser = new FileChooser();

    @FXML
    void initialize() {
        secretKey_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        // Устанавливаем начальное состояние кнопки выбора файла
        encrypt_button.setDisable(true);

        // Добавляем слушатель событий на текстовое поле
        secretKey_textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Проверяем, является ли введенное значение числом, находится ли в допустимом диапазоне и не является ли нулем
            boolean isNumeric = newValue.matches("-?\\d+");
            int value = isNumeric ? Integer.parseInt(newValue) : 0;
            boolean isValidRange = value >= -83 && value <= 83;
            // Устанавливаем состояние активности кнопки выбора файла в зависимости от валидности значения
            encrypt_button.setDisable(!isNumeric || !isValidRange);
        });

        encrypt_button.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                try {
                    // Получаем сдвиг из secretKeyTextField
                    int shift = Integer.parseInt(secretKey_textField.getText());

                    // Чтение данных из файла .txt
                    StringBuilder inputData = new StringBuilder();
                    Scanner scanner = new Scanner(selectedFile);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        inputData.append(line).append("\n");
                    }
                    scanner.close();

                    // Шифрование данных методом Цезаря с кастомным алфавитом
                    String encryptedData = caesarEncrypt(inputData.toString(), shift);

                    // Создаем путь для сохранения файла .dat в той же директории, что и .txt
                    String parentDirectory = selectedFile.getParent();
                    String outputFilePath = parentDirectory + File.separator + "laba2_encrypt.dat";

                    // Сохранение зашифрованных данных в файле .dat
                    FileWriter fileWriter = new FileWriter(outputFilePath);
                    fileWriter.write(encryptedData);
                    fileWriter.close();

                    resultField_label.setText("Данные зашифрованы и сохранены в " + outputFilePath);
                } catch (NumberFormatException | IOException e) {
                    // Выводим сообщение об ошибке, если пользователь ввел недопустимое значение
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Пожалуйста, введите числовое значение для ключа в диапазоне от -83 до 83 (кроме 0).");
                    alert.showAndWait();
                }
            }
        });
        // Устанавливаем начальное состояние кнопки выбора файла
        decrypt_button.setDisable(true);

        // Добавляем слушатель событий на текстовое поле
        secretKey_textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Проверяем, является ли введенное значение числом, находится ли в допустимом диапазоне и не является ли нулем
            boolean isNumeric = newValue.matches("-?\\d+");
            int value = isNumeric ? Integer.parseInt(newValue) : 0;
            boolean isValidRange = value >= -83 && value <= 83;
            // Устанавливаем состояние активности кнопки выбора файла в зависимости от валидности значения
            decrypt_button.setDisable(!isNumeric || !isValidRange);
        });

        decrypt_button.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Data Files", "*.dat"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                try {
                    // Получаем сдвиг из secretKeyTextField
                    int shift = Integer.parseInt(secretKey_textField.getText());

                    // Чтение данных из файла .dat
                    StringBuilder inputData = new StringBuilder();
                    Scanner scanner = new Scanner(selectedFile);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        inputData.append(line).append("\n");
                    }
                    scanner.close();

                    // Расшифрование данных методом Цезаря с кастомным алфавитом
                    String decryptedData = caesarDecrypt(inputData.toString(), shift);

                    // Создаем путь для сохранения файла .txt в той же директории, что и .dat
                    String parentDirectory = selectedFile.getParent();
                    String outputFilePath = parentDirectory + File.separator + "laba2_decrypt.txt";

                    // Сохранение расшифрованных данных в файле .txt
                    FileWriter fileWriter = new FileWriter(outputFilePath);
                    fileWriter.write(decryptedData);
                    fileWriter.close();

                    resultField_label.setText("Данные расшифрованы и сохранены в " + outputFilePath);
                } catch (NumberFormatException | IOException e) {
                    // Выводим сообщение об ошибке, если пользователь ввел недопустимое значение
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Пожалуйста, введите числовое значение для ключа в диапазоне от -83 до 83 (кроме 0).");
                    alert.showAndWait();
                }
            }
        });
        // вернуть оглавление
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
        System.out.println();
    }

    private String caesarEncrypt(String input, int shift) {
        // Кастомный алфавит, включая русские буквы, цифры и знаки препинания
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ 123456789.,:!?()";

        StringBuilder result = new StringBuilder();

        for (char character : input.toCharArray()) {
            int index = alphabet.indexOf(character);
            if (index != -1) {
                int shiftedIndex = (index + shift) % alphabet.length();
                result.append(alphabet.charAt(shiftedIndex));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    private String caesarDecrypt(String input, int shift) {
        // Кастомный алфавит, включая русские буквы, цифры и знаки препинания
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ 123456789.,:!?()";

        StringBuilder result = new StringBuilder();

        for (char character : input.toCharArray()) {
            int index = alphabet.indexOf(character);
            if (index != -1) {
                int shiftedIndex = (index - shift + alphabet.length()) % alphabet.length();
                result.append(alphabet.charAt(shiftedIndex));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }
}