package com.example.decode_lab;

import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class lab8RSAController {

//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;

    @FXML
    private Button backContents_buttons;

//    @FXML
//    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Button decryptText_button;

//    @FXML
//    private Label decrypt_label;

    @FXML
    private Button encryptText_button;

    @FXML
    private TextField inputText_textField;

    @FXML
    private AnchorPane mainPane_anchorPane;

    @FXML
    private TextField outputText_textField;

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
        inputText_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        outputText_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        /////////////////////////////////////////////////
        Security.addProvider(new BouncyCastleProvider());
        KeyPair keyPair = generateKeyPair();

        encryptText_button.setOnAction(event -> {
            try {
                String plainText = inputText_textField.getText();
                byte[] encryptedBytes = encrypt(plainText, keyPair.getPublic());
                String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
                outputText_textField.setText(encryptedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        decryptText_button.setOnAction(event -> {
            try {
                String encryptedText = inputText_textField.getText();
                byte[] decryptedBytes = decrypt(encryptedText, keyPair.getPrivate());
                String decryptedText = new String(decryptedBytes);
                outputText_textField.setText(decryptedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Error generating key pair", e);
        }
    }

    private byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes());
    }

    private byte[] decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(Base64.getDecoder().decode(encryptedText));
    }

}
