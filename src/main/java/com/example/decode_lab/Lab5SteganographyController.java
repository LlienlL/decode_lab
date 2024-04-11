package com.example.decode_lab;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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

import javax.swing.text.html.ImageView;

public class Lab5SteganographyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Picture_imageView;

    @FXML
    private Button backContents_buttons;

    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Button decrypt_button;

    @FXML
    private Button encrypt_button;

    @FXML
    private Button inputImage_button;

    @FXML
    private TextField input_textField;

    @FXML
    private TextField keyInputTextField;

    @FXML
    private AnchorPane mainPane_anchorPane;

    @FXML
    private Label result_label;

    @FXML
    void initialize() {
        // кнопка возврата
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
        input_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    }
}