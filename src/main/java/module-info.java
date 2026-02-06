module com.example.streamingplatformfeedback {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.streamingplatformfeedback to javafx.fxml;
    exports com.example.streamingplatformfeedback;
    exports com.example.streamingplatformfeedback.ui;
    exports com.example.streamingplatformfeedback.model;
    opens com.example.streamingplatformfeedback.ui to javafx.fxml;
}