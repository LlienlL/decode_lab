package com.example.decode_lab.auth_wind;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WindowRole2Controller {

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
    private Button createTable_button;

    @FXML
    private Button edutTable_button;


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
        edutTable_button.setOnAction(e -> enableEditing());
        createTable_button.setOnAction(e -> createNewRecord());
        
        TableColumn<FileData, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        idColumn.setSortable(false);

        TableColumn<FileData, String> filenameColumn = new TableColumn<>("Имя файла");
        filenameColumn.setCellValueFactory(cellData -> cellData.getValue().filenameProperty());
        filenameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        filenameColumn.setOnEditCommit(event -> updateDatabase(event));

        TableColumn<FileData, String> creationDateColumn = new TableColumn<>("Дата создания");
        creationDateColumn.setCellValueFactory(cellData -> cellData.getValue().creationDateProperty());
        creationDateColumn.setSortable(false);

        TableColumn<FileData, String> modificationDateColumn = new TableColumn<>("Дата изменения");
        modificationDateColumn.setCellValueFactory(cellData -> cellData.getValue().modificationDateProperty());
        modificationDateColumn.setSortable(false);

        TableColumn<FileData, Integer> userIdColumn = new TableColumn<>("ID пользователя");
        userIdColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());
        userIdColumn.setSortable(false);

        mainTable_tableView.getColumns().add(idColumn);
        mainTable_tableView.getColumns().add(filenameColumn);
        mainTable_tableView.getColumns().add(creationDateColumn);
        mainTable_tableView.getColumns().add(modificationDateColumn);
        mainTable_tableView.getColumns().add(userIdColumn);

        mainTable_tableView.setEditable(true);
    }

    private void createNewRecord() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO files (filename, creation_date, modification_date, user_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Замените следующую строку на логику получения значения для новой записи
                String newFilename = "Новый файл";

                // Получаем текущую дату
                LocalDate currentDate = LocalDate.now();

                // Преобразуем LocalDate в java.sql.Date
                java.sql.Date sqlCurrentDate = java.sql.Date.valueOf(currentDate);

                preparedStatement.setString(1, newFilename);
                preparedStatement.setDate(2, sqlCurrentDate); // Используем setDate для столбца типа date
                preparedStatement.setDate(3, sqlCurrentDate); // Используем setDate для столбца типа date
                preparedStatement.setInt(4, 2); // Замените на актуальный ID пользователя

                preparedStatement.executeUpdate();
            }
            refreshTable();  // Обновляем таблицу после добавления новой записи
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("SQL Error: " + ex.getMessage());
        }
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

    private void enableEditing() {
        mainTable_tableView.setEditable(true);
    }

    private void updateDatabase(TableColumn.CellEditEvent<FileData, String> event) {
        int editedIndex = event.getTablePosition().getRow();
        FileData editedFileData = event.getTableView().getItems().get(editedIndex);
        String columnName = event.getTableColumn().getText();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updateQuery = "UPDATE files SET " + getDatabaseColumnName(columnName) + " = ?, modification_date = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, event.getNewValue());

                // Устанавливаем modification_date в текущую дату
                LocalDate currentDate = LocalDate.now();
                java.sql.Date sqlCurrentDate = java.sql.Date.valueOf(currentDate);
                preparedStatement.setDate(2, sqlCurrentDate);

                preparedStatement.setInt(3, editedFileData.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Error: " + e.getMessage());
        }
    }


    private String getDatabaseColumnName(String columnName) {
        // Замените следующий код на логику преобразования имени столбца на имя в базе данных
        // Например, если columnName равно "Имя файла", верните "filename"
        if ("Имя файла".equals(columnName)) {
            return "filename";
        } else if ("Дата создания".equals(columnName)) {
            return "creation_date";
        } else if ("Дата изменения".equals(columnName)) {
            return "modification_date";
        } else if ("ID пользователя".equals(columnName)) {
            return "user_id";
        }
        return columnName;
    }

}