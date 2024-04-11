package com.example.decode_lab.auth_wind;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WindowRole1Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/decode_lab_base";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123";

    @FXML
    private Button backContents_buttons;

    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private TableView<FileData> mainTable_tableView;

    @FXML
    private Button showTable_tableView;

    @FXML
    void initialize() {
        backContents_buttons.setOnAction(event -> {
            backContents_buttons.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("/com/example/decode_lab/lab9AuthUsers.fxml")));
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
        mainTable_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        showTable_tableView.setOnAction(e -> refreshTable());

        TableColumn<FileData, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<FileData, String> filenameColumn = new TableColumn<>("Имя файла");
        filenameColumn.setCellValueFactory(cellData -> cellData.getValue().filenameProperty());

        TableColumn<FileData, String> creationDateColumn = new TableColumn<>("Дата создания");
        creationDateColumn.setCellValueFactory(cellData -> cellData.getValue().creationDateProperty());

        TableColumn<FileData, String> modificationDateColumn = new TableColumn<>("Дата изменения");
        modificationDateColumn.setCellValueFactory(cellData -> cellData.getValue().modificationDateProperty());

        TableColumn<FileData, Integer> userIdColumn = new TableColumn<>("ID пользователя");
        userIdColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());

        mainTable_tableView.getColumns().add(idColumn);
        mainTable_tableView.getColumns().add(filenameColumn);
        mainTable_tableView.getColumns().add(creationDateColumn);
        mainTable_tableView.getColumns().add(modificationDateColumn);
        mainTable_tableView.getColumns().add(userIdColumn);


    }

    private void refreshTable() {
        ObservableList<FileData> data = fetchDataFromDatabase();
        mainTable_tableView.setItems(data);
    }

    private ObservableList<FileData> fetchDataFromDatabase() {
        ObservableList<FileData> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM files";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String filename = resultSet.getString("filename");
                String creationDate = resultSet.getString("creation_date");
                String modificationDate = resultSet.getString("modification_date");
                int userId = resultSet.getInt("user_id");

                FileData fileData = new FileData(id, filename, creationDate, modificationDate, userId);
                data.add(fileData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}

