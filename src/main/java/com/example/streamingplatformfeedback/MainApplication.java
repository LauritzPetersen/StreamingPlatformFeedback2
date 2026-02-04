package com.example.streamingplatformfeedback;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        var url = getClass().getResource("/login-view.fxml");
//
//        System.out.println("FXML url = " + url);
//
//        if (url == null) {
//            throw new IllegalStateException("FXML not found in resources");
//        }

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("StreamingPlatform!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


