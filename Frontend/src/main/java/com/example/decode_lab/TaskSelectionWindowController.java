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
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TaskSelectionWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Text contents_label;

    @FXML
    private Text list_text;

    @FXML
    private AnchorPane mainInfo_anchorPane;

    @FXML
    private AnchorPane otherInfo_anchorPane;
    @FXML
    private Button selectedJob_button;

    @FXML
    private Label taskConditions_label;

    @FXML
    private SplitPane task_splitPane;

    @FXML
    private ToggleButton vrapperColom_toggleButton;

    @FXML
    private Button work10_button;

    @FXML
    private Button work11_button;

    @FXML
    private Button work12_button;

    @FXML
    private Button work1_button;

    @FXML
    private Button work2_button;

    @FXML
    private Button work3_button;

    @FXML
    private Button work4_button;

    @FXML
    private Button work5_button;

    @FXML
    private Button work6_button;

    @FXML
    private Button work7_button;

    @FXML
    private Button work8_button;

    @FXML
    private Button work9_button;
    int counter_button = 0;
    String nameWindow = "TaskSelectionWindow.fxml";

    @FXML
    void initialize() {
        // тут работаем с врапером
        vrapperColom_toggleButton.setOnAction(event -> {
            if (vrapperColom_toggleButton.isSelected()) {
                // Если кнопка выбрана, будет цирк
                task_splitPane.setDividerPosition(0, 0.15);
                vrapperColom_toggleButton.setText("<");
                colorCounter_toggleButton.setLayoutX(543);
                contents_label.setLayoutX(190);
                selectedJob_button.setLayoutX(179);
                taskConditions_label.setLayoutX(5);
            } else {
                // Если кнопка не выбрана, цирк в большом маштабе
                task_splitPane.setDividerPosition(0, 0);
                vrapperColom_toggleButton.setText(">");
                colorCounter_toggleButton.setLayoutX(648);
                contents_label.setLayoutX(295);
                selectedJob_button.setLayoutX(284);
                taskConditions_label.setLayoutX(110);
            }

        });
        // кнопка задания. Задание один
        work1_button.setOnAction(event -> {
            taskConditions_label.setText("Создать программу, которая будет брать файл (формат .txt) шифровать его после чего сохранять там же (формат .dat). Перед тем как попасть в программу нужно будет ввести пароль, если ввести его не правильно 3 раза, программа закроется.");
            counter_button = 1;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание два
        work2_button.setOnAction(event -> {
            taskConditions_label.setText("Написать программу на вход которой будет поступать файл (фомат .txt) они будет шифровать методом Цезаря (пользователь вводит сдвиг) после чего шифрует в туже папку (формат .dat). Перед тем как попасть в программу нужно будет ввести пароль, если ввести его не правильно 3 раза, программа закроется.");
            counter_button = 2;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание третье
        work3_button.setOnAction(event -> {
            taskConditions_label.setText("Написать программу на входе которй пользователь будет вводить ключ (слово), а так же сам текст (или можно загрузить из .txt) и выводить результат шифрования методом Веинера  поле (или в .dat). Перед тем как попасть в программу нужно будет ввести пароль, если ввести его не правильно 3 раза, программа закроется. ");
            counter_button = 3;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание четвёртое
        work4_button.setOnAction(event -> {
            taskConditions_label.setText("написать программу стеганографию. всё как обычно");
            counter_button = 4;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание пять
        work5_button.setOnAction(event -> {
            taskConditions_label.setText("Написать прогррамму, которая на вход фразу которую нужно зашифровать и ключ для составления сетки, шифрует (показывая сетку) и даёт на выходе последовательнсоть цифр и при внесени в поле шифр зашифрованного текста нажимая кнопку расшифровать будет получен результат. Перед тем как попасть в программу нужно будет ввести пароль, если ввести его не правильно 3 раза, программа закроется.");
            counter_button = 5;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание шесть
        work6_button.setOnAction(event -> {
            taskConditions_label.setText("создать систему аутентификации с помощью односторонней функции. при входе в программу пользователь будет вводить известный код, после чего данный код будет шифроватся n-раз и проверятся на сервере если он совпадает, тогда сервер разрешит войти.");
            counter_button = 6;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание семь
        work7_button.setOnAction(event -> {
            taskConditions_label.setText("7");
            counter_button = 7;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание восемь
        work8_button.setOnAction(event -> {
            taskConditions_label.setText("создать rsa ");
            counter_button = 8;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание девять
        work9_button.setOnAction(event -> {
            taskConditions_label.setText("Форма авторизации с учетом ролей пользователей.");
            counter_button = 9;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание десять
        work10_button.setOnAction(event -> {
            taskConditions_label.setText("10");
            counter_button = 10;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание одиннадцать
        work11_button.setOnAction(event -> {
            taskConditions_label.setText("11");
            counter_button = 11;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        // кнопка задания. Задание двенадцать
        work12_button.setOnAction(event -> {
            taskConditions_label.setText("12");
            counter_button = 12;
            vrapperColom_toggleButton.setText(">");
            vrapperColom_toggleButton.setSelected(false);
            task_splitPane.setDividerPosition(0, 0);
            colorCounter_toggleButton.setLayoutX(648);
            contents_label.setLayoutX(295);
            selectedJob_button.setLayoutX(284);
            taskConditions_label.setLayoutX(110);
        });
        selectedJob_button.setOnAction(event -> {
            switch (counter_button) {
                case 1:
                    nameWindow = "Working-decoder.fxml";
                    break;
                case 2:
                    nameWindow = "lab2CaesarsCipher.fxml";
                    break;
                case 3:
                    nameWindow = "lab3.fxml";
                    break;
                case 4:
                    nameWindow = "lab5Steganography.fxml";
                    break;
                case 5:
                    nameWindow = "lab6CardanoGrid.fxml";
                    break;
                case 6:
                    nameWindow = "hello-view.fxml";
                    break;
                case 7:
                    nameWindow = "TaskSelectionWindow.fxml";
                    break;
                case 8:
                    nameWindow = "lab8RSA.fxml";
                    break;
                case 9:
                    nameWindow = "lab9AuthUsers.fxml";
                    break;
                case 10:
                    nameWindow = "TaskSelectionWindow.fxml";
                    break;
                case 11:
                    nameWindow = "TaskSelectionWindow.fxml";
                    break;
                case 12:
                    nameWindow = "TaskSelectionWindow.fxml";
                    break;
                default:
                    nameWindow = "TaskSelectionWindow.fxml";
                    break;
            }
            selectedJob_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(nameWindow)));
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

}

