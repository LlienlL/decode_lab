package com.example.decode_lab;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.net.URL;
import java.security.spec.KeySpec;
import java.util.ResourceBundle;

public class WorkingDecoderController {

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
    private Button backContents_buttons;

    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Label encrypt_label;

    @FXML
    private TextField encrypt_textField;

    @FXML
    private TextField decrypt_textField;

    @FXML
    private AnchorPane mainPane_anchorPane;

    @FXML
    void initialize() {
        encrypt_button.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                try {
                    String inputFilePath = selectedFile.getAbsolutePath();
                    String key = "SecretKey"; //  секретный ключ

                    // Чтение данных из файла .txt
                    BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                    StringBuilder inputData = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        inputData.append(line).append("\n");
                    }
                    reader.close();

                    // Шифрование данных
                    byte[] encryptedData = encrypt(inputData.toString(), key);

                    // Создаем путь для сохранения файла .dat в той же директории, что и .txt
                    String parentDirectory = new File(inputFilePath).getParent();
                    String outputFilePath = parentDirectory + File.separator + "encrypted.dat";

                    // Сохранение зашифрованных данных в файл .dat
                    FileOutputStream outputStream = new FileOutputStream(outputFilePath);
                    outputStream.write(encryptedData);
                    outputStream.close();

                    encrypt_label.setText("Данные зашифрованы и сохранены в ");
//                    encrypt_textField.setStyle("-fx-text-fill: white");
                    encrypt_textField.setText(outputFilePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        decrypt_button.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Data Files", "*.dat"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                try {
                    String inputFilePath = selectedFile.getAbsolutePath();
                    String key = "SecretKey"; //  секретный ключ

                    // Расшифровка данных
                    byte[] decryptedData = decryptFile(inputFilePath, key);

                    // Создаем путь для сохранения файла .txt в той же директории, что и .dat
                    String parentDirectory = new File(inputFilePath).getParent();
                    String outputFilePath = parentDirectory + File.separator + "decoded.txt";

                    // Сохранение расшифрованных данных в файле .txt
                    FileOutputStream outputStream = new FileOutputStream(outputFilePath);
                    outputStream.write(decryptedData);
                    outputStream.close();

                    decrypt_label.setText("Данные расшифрованы и сохранены в ");
//                    decrypt_textField.setStyle("-fx-text-fill: white");
                    decrypt_textField.setText(outputFilePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
            stage.show();
            stage.setTitle("Susu_422_decoder");
        });
    }

    public static byte[] encrypt(String inputData, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        KeySpec keySpec = new DESKeySpec(key.getBytes("UTF8"));
        SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
        cipherOutputStream.write(inputData.getBytes());
        cipherOutputStream.close();

        return outputStream.toByteArray();
    }

    public static byte[] decryptFile(String inputFilePath, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        KeySpec keySpec = new DESKeySpec(key.getBytes("UTF8"));
        SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        FileInputStream inputStream = new FileInputStream(inputFilePath);
        CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        cipherInputStream.close();

        return outputStream.toByteArray();
    }
}


