package com.example.decode_lab;

import java.io.IOException;
import java.util.*;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Lab6CardanoGridController {


    @FXML
    private Button FAQ_Button;

    @FXML
    private ScrollBar ScrollBar;

    @FXML
    private Button backContents_buttons;

    @FXML
    private ToggleButton colorCounter_toggleButton;

    @FXML
    private Button decrypt_button;

    @FXML
    private Label decrypt_label;

    @FXML
    private Button restart_button;

    @FXML
    private Button encryp_button;
    @FXML
    private Label keyX_label;

    @FXML
    private Label keyY_label;
    @FXML
    private TextArea KeyX_textArea;

    @FXML
    private TextArea KeyY_textArea;

    @FXML
    private TextField inut_textField;

    @FXML
    private Label label;

    @FXML
    private AnchorPane mainPane_anchorPane;

    @FXML
    private TextField result_textField;
    int peremennaia;
    private List<Integer> ListCkickX = new ArrayList<>();
    private List<Integer> ListCkicky = new ArrayList<>();
    @FXML
    private TableView<ObservableList<String>> tableView;
    private Set<Integer> disabledValues = new HashSet<>(); // Объявление переменной

    private static DoubleProperty rowCount = new SimpleDoubleProperty();
    private static DoubleProperty columnCount = new SimpleDoubleProperty();

    private List<String> ListClick = new ArrayList<>();

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
        //кнопка перезапуска
        restart_button.setOnAction(event -> {
            backContents_buttons.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("lab6CardanoGrid.fxml")));
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
        // Style button
        result_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
//        keyInput_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        inut_textField.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");
        tableView.setStyle("-fx-text-fill: #e7e7e7; -fx-background-color: #3d3d3d;");

        encryp_button.setDisable(true);
        decrypt_button.setDisable(true);
        ScrollBar.setDisable(true);

        inut_textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Разбиваем введенный текст на слова, используя пробел как разделитель
            String[] words = newValue.split("\\s+");

            // Проверяем каждое слово на длину
            boolean hasLongWord = false;
            for (String word : words) {
                if (word.length() >= 3) {
                    hasLongWord = true;
                    break;
                }
            }

            // Устанавливаем состояние активности кнопки в зависимости от наличия длинных слов
            encryp_button.setDisable(!hasLongWord);
            decrypt_button.setDisable(!hasLongWord);
            ScrollBar.setDisable(!hasLongWord);
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        encryp_button.setOnAction(event -> {
            peremennaia = (int) Math.ceil(Math.sqrt(inut_textField.getText().length() + 1));

        });

        // Слушатель изменения значения ScrollBar
        ScrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            peremennaia = (int) Math.ceil(Math.sqrt(inut_textField.getText().length() + 1));
            int roundedValue = (int) Math.round(newValue.doubleValue());
            updateTableSize(roundedValue * 2);
            label.setText("( " + roundedValue + " )");
            ScrollBar.setMin(peremennaia);
            encryp_button.setOnAction(event -> handleButID(roundedValue*2));
            decrypt_button.setOnAction(event -> {
                handleButID2(roundedValue*2);
                System.out.println("ключи:" + KeyX_textArea.getText() +" "+ KeyY_textArea.getText());
            });
        });

    }
        private void handleButID(int rowCountAndColumnCount) {
            // Получить текст из Poletext
            String inputText = inut_textField.getText();

            // Проверить, есть ли достаточно символов во входном тексте для заполнения массива
            if (inputText.length() < ListCkickX.size()) {
                result_textField.setText("Недостаточно символов для заполнения массива.");
                return;
            }

            char[][] resultArray = new char[rowCountAndColumnCount][rowCountAndColumnCount];

            // Заполнить массив случайными буквами
            Random random = new Random();
            for (int i = 0; i < rowCountAndColumnCount; i++) {
                for (int j = 0; j < rowCountAndColumnCount; j++) {
                    resultArray[i][j] = (char) ('A' + random.nextInt(26)); // Генерация случайной буквы
                }
            }

            // Вставить символы из входного текста в массив согласно координатам
            for (int k = 0; k < ListCkickX.size(); k++) {
                int x = ListCkickX.get(k) - 1; // Координаты начинаются с 1
                int y = ListCkicky.get(k) - 1; // Координаты начинаются с 1
                if (x >= 0 && x < rowCountAndColumnCount && y >= 0 && y < rowCountAndColumnCount) {
                    resultArray[x][y] = inputText.charAt(k);
                }
            }

            // Вывести результат в лейбл RezultText
            StringBuilder resultText = new StringBuilder();
            for (int i = 0; i < rowCountAndColumnCount; i++) {
                for (int j = 0; j < rowCountAndColumnCount; j++) {
                    resultText.append(resultArray[i][j]);
                    resultText.append(' '); // Добавить пробел между символами для разделения
                }
                resultText.append('\n'); // Переход на новую строку
            }
            String str = resultText.toString();
            result_textField.setText(str.replaceAll("\\s", ""));
        }
    @FXML
    private void handleButID2(int rowCountAndColumnCount) {
        String inputText = inut_textField.getText();
        String key1Text = KeyY_textArea.getText();
        String key2Text = KeyX_textArea.getText();

        // Уберите пробелы и символы "[" и "]" из текста
        key1Text = key1Text.replaceAll("[\\s\\[\\]]", "");
        key2Text = key2Text.replaceAll("[\\s\\[\\]]", "");

        String[] key1Array = key1Text.split(",");
        String[] key2Array = key2Text.split(",");

        int size = (int) Math.sqrt(inut_textField.getText().length()); // Размер массива

        // Создаем квадратный массив resultArray
        char[][] resultArray = new char[size][size];

        int textIndex = 0; // Индекс для прохода по символам вводного текста

        // Заполняем resultArray символами из Poletext слева направо, сверху вниз
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (textIndex < inputText.length()) {
                    resultArray[i][j] = inputText.charAt(textIndex);
                    textIndex++;
                }
            }
        }

        // Очищаем RezultText
        result_textField.clear();
        // Записываем символы из resultArray в RezultText
        for (int k = 0; k < key1Array.length; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == Integer.parseInt(key1Array[k])-1 && (j == Integer.parseInt(key2Array[k])-1))
                    {
                        result_textField.appendText(Character.toString(resultArray[i][j]));
                        System.out.println(resultArray[i][j]);

                    }
                }
            }
        }
    }



    private void updateTableSize(int rowCountAndColumnCount) {
        int[][] arrayListZnach = returnListTable(rowCountAndColumnCount/2);
        tableView.getItems().clear();
        tableView.getColumns().clear();

        for (int i = 0; i < rowCountAndColumnCount; i++) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>("№ " + (i + 1));
            final int columnIndex = i;
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            column.setCellValueFactory(cellData -> {
                ObservableList<String> row = cellData.getValue();
                return new SimpleStringProperty(row.get(columnIndex));
            });

            column.setCellFactory(tc -> new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Вместо "Row" выводим числа, начиная с 1

                        int value = arrayListZnach[getIndex()][columnIndex];
                        setText(Integer.toString(value));
                        // Проверяем, заблокировано ли значение
                        if (disabledValues.contains(value)) {
                            setDisable(true);
                            setStyle("-fx-background-color: grey;");
                        } else {
                            setDisable(false);
                            setStyle("");
                        }

                        setOnMouseClicked(event -> {
                            if (!disabledValues.contains(value)) {
                                ListCkickX.add(getIndex() + 1);
                                ListCkicky.add(columnIndex + 1);
                                System.out.println("Row: " + (getIndex() + 1) + ", Column: " + (columnIndex + 1));
                                KeyX_textArea.setText(String.valueOf(ListCkicky));
                                KeyY_textArea.setText(String.valueOf(ListCkickX));
                                // Блокируем значение
                                disabledValues.add(value);
                                setDisable(true);
                                setStyle("-fx-background-color: grey;");

                                // Отражаем значение в другой четверти
                                int oppositeIndex = rowCountAndColumnCount - getIndex() - 1;
                                tableView.getItems().get(oppositeIndex).get(columnIndex);

                                // Вывод содержимого disabledValues в консоль
                                System.out.println("Заблокированные значения: " + disabledValues);
                                // Вывод содержимого ListClick в консоль
                                System.out.println("Серые ячейки: " + ListClick);
                            }

                        });
                    }
                }
            });

            tableView.getColumns().add(column);
        }

        for (int i = 0; i < rowCountAndColumnCount; i++) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int j = 0; j < rowCountAndColumnCount; j++) {
                // Генерируем числа, начиная с 1
                int value = (i * rowCountAndColumnCount + j) + 1;
                row.add(Integer.toString(value));
            }
            tableView.getItems().add(row);
        }
    }
    public static int[][] returnListTable(int N) {
        int[][] matrix = new int[N][N];

        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = num;
                num++;
            }
        }

        int[][] transposedMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                transposedMatrix[i][j] = matrix[j][i];
            }
        }

        int[][] reflectedMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                reflectedMatrix[i][j] = transposedMatrix[i][N - 1 - j];
            }
        }

        int[][] matrix1 = new int[N][N * 2];

        for (int i = 0; i < N; i++) {
            System.arraycopy(matrix[i], 0, matrix1[i], 0, N);
            System.arraycopy(reflectedMatrix[i], 0, matrix1[i], N, N);
        }

        int[][] transposedMatrix2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                transposedMatrix2[i][j] = reflectedMatrix[j][i];
            }
        }

        int[][] reflectedMatrix2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                reflectedMatrix2[i][j] = transposedMatrix2[i][N - 1 - j];
            }
        }

        int[][] transposedMatrix3 = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                transposedMatrix3[i][j] = reflectedMatrix2[j][i];
            }
        }

        int[][] reflectedMatrix3 = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                reflectedMatrix3[i][j] = transposedMatrix3[i][N - 1 - j];
            }
        }

        int[][] matrix2 = new int[N][N * 2];

        for (int i = 0; i < N; i++) {
            System.arraycopy(reflectedMatrix2[i], 0, matrix2[i], N, N);
            System.arraycopy(reflectedMatrix3[i], 0, matrix2[i], 0, N);
        }

        int[][] arrayListZnach = new int[2 * N][N * 2];

        for (int i = 0; i < 2 * N; i++) {
            if (i < N) {
                System.arraycopy(matrix1[i], 0, arrayListZnach[i], 0, N * 2);
            } else {
                System.arraycopy(matrix2[i - N], 0, arrayListZnach[i], 0, N * 2);
            }
        }

        // Вывод объединенной транспонированной матрицы
        for (int i = 0; i < arrayListZnach.length; i++) {
            // Перебор элементов внутри строки
            for (int j = 0; j < arrayListZnach[i].length; j++) {
                System.out.print(arrayListZnach[i][j] + " ");
            }
            System.out.println(); // Переход на новую строку для следующей строки
        }
        return arrayListZnach;
    }
}