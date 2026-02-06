package com.example.streamingplatformfeedback;

import com.example.streamingplatformfeedback.infrastructure.DbConfig;
import com.example.streamingplatformfeedback.model.User;
import com.example.streamingplatformfeedback.repository.UserRepository;
import com.example.streamingplatformfeedback.service.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("StreamingPlatform!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
       launch(args);
   }
}


