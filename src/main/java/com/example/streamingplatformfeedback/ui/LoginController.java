package com.example.streamingplatformfeedback.ui;

import com.example.streamingplatformfeedback.infrastructure.DbConfig;
import com.example.streamingplatformfeedback.model.User;
import com.example.streamingplatformfeedback.repository.FavoriteRepository;
import com.example.streamingplatformfeedback.repository.MovieRepository;
import com.example.streamingplatformfeedback.repository.UserRepository;
import com.example.streamingplatformfeedback.service.FavoriteService;
import com.example.streamingplatformfeedback.service.MovieService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;


import com.example.streamingplatformfeedback.service.UserService;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;


public class LoginController {


    @FXML private TextField emailField;
    @FXML private Button loginButton;


    @FXML public void onLoginButtonClick(ActionEvent event){
        String email = emailField.getText();

        try{
            List<User> users = userService.findByEmail(email);
            if(users != null && !users.isEmpty()){
                User foundUser = users.get(0);
                SceneSwitch.switchToStreaming(event, foundUser, movieService, favService);
            }
            else{
                showAlert("Login Failed", "no user found");
            }
        } catch(Exception e){
            showAlert("Login Failed", "An error occurred during login: ");
        }
    }


    private UserService userService;
    private FavoriteService favService;
    private MovieService movieService;

    public void initialize() {
        DbConfig db = new DbConfig();
        UserRepository repo = new UserRepository(db);
        MovieRepository movieRepo = new MovieRepository(db);
        FavoriteRepository favRepo = new FavoriteRepository(db);
        userService = new UserService(repo);
        movieService = new MovieService(movieRepo);
        favService = new FavoriteService(favRepo);

    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
