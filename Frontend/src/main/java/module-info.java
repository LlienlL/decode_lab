module com.example.decode_lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires org.bouncycastle.provider;
    requires spring.web;
    requires java.sql;

    opens com.example.decode_lab to javafx.fxml; // если хочу открыть в папке тогда opens com.example.decode_lab.auth_wind to javafx.fxml; не в папке opens com.example.decode_lab to javafx.fxml
    opens com.example.decode_lab.auth_wind to javafx.fxml;
    exports com.example.decode_lab;
    exports com.example.decode_lab.auth_wind;
}

