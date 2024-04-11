package com.example.decode_lab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class test_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enter_button;

    @FXML
    private TextField inputCode_textField;
    String code = "";
    Integer count_code;

    @FXML
    private Label resultOut_label;

    @FXML
    void initialize() {
      enter_button.setOnAction(event -> {
          code = inputCode_textField.getText();
      });
    }

}
