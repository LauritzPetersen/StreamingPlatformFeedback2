package com.example.streamingplatformfeedback.ui;

import com.example.streamingplatformfeedback.model.User;
import com.example.streamingplatformfeedback.service.FavoriteService;
import com.example.streamingplatformfeedback.service.MovieService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class SceneSwitch {

    public static void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(SceneSwitch.class.getResource(fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void switchToStreaming(ActionEvent event, User user, MovieService movieService, FavoriteService favoriteService) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneSwitch.class.getResource("/streaming-view.fxml"));
        Parent root = loader.load();
        StreamingServiceController controller = loader.getController();
        controller.initializeData(user, movieService, favoriteService);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}