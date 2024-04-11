package com.example.decode_lab.auth_wind;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileData {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty filename;
    private final SimpleStringProperty creationDate;
    private final SimpleStringProperty modificationDate;
    private final SimpleIntegerProperty userId;

    public FileData(int id, String filename, String creationDate, String modificationDate, int userId) {
        this.id = new SimpleIntegerProperty(id);
        this.filename = new SimpleStringProperty(filename);
        this.creationDate = new SimpleStringProperty(creationDate);
        this.modificationDate = new SimpleStringProperty(modificationDate);
        this.userId = new SimpleIntegerProperty(userId);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getFilename() {
        return filename.get();
    }

    public SimpleStringProperty filenameProperty() {
        return filename;
    }

    public String getCreationDate() {
        return creationDate.get();
    }

    public SimpleStringProperty creationDateProperty() {
        return creationDate;
    }

    public String getModificationDate() {
        return modificationDate.get();
    }

    public SimpleStringProperty modificationDateProperty() {
        return modificationDate;
    }

    public int getUserId() {
        return userId.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }
}
