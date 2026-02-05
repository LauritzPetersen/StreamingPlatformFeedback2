package com.example.streamingplatformfeedback.ui;

import com.example.streamingplatformfeedback.model.Favorite;
import com.example.streamingplatformfeedback.model.Movie;
import com.example.streamingplatformfeedback.model.User;
import com.example.streamingplatformfeedback.service.FavoriteService;
import com.example.streamingplatformfeedback.service.MovieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class StreamingServiceController {

    private User currentUser;
    private MovieService movieService;
    private FavoriteService favoriteService;

    // Tableviews and columns for movies and favorites

    @FXML
    private TableView<Favorite> favoriteTableView;
    @FXML
    private TableColumn<Favorite, String> favoriteTitleColumn;
    @FXML
    private TableColumn<Favorite, Double> favoriteRatingColumn;
    @FXML
    private TableColumn<Favorite, String> favoriteGenreColumn;

    @FXML
    private TableView<Movie> movieTableView;
    @FXML
    private TableColumn<Movie, String> movieTitleColumn;
    @FXML
    private TableColumn<Movie, Double> movieRatingColumn;
    @FXML
    private TableColumn<Movie, String> movieGenreColumn;

    @FXML
    private Label nameText;
    @FXML
    private Label emailText;

    @FXML
    private Button addFavorite;
    @FXML
    private Button removeFavorite;
    @FXML
    private Button logOut;

    @FXML
    public void initializeData(User user, MovieService movieService, FavoriteService favoriteService) {
        this.currentUser = user;
        this.movieService = movieService;
        this.favoriteService = favoriteService;

        nameText.setText("Bruger: " + user.getName());
        emailText.setText("Email: " + user.getEmail());


        setupMovieTable();
        setupFavoriteTable();

        loadMovies();
        loadFavorites();
    }


    @FXML
    private void handleAddFavorite(ActionEvent event) {
        Movie selectedMovie = movieTableView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            try {
                favoriteService.addToFavorites(currentUser.getId(), selectedMovie.getId());
                loadFavorites(); // Opdater listen med det samme
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleRemoveFavorite(ActionEvent event) {
        Favorite selectedFavorite = favoriteTableView.getSelectionModel().getSelectedItem();
        if (selectedFavorite != null) {
            try {
                favoriteService.removeFromFavorites(currentUser.getId(), selectedFavorite.getMovieId());
                loadFavorites();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
        try {
            SceneSwitch.switchScene(event, "/login-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMovies() {
        try {
            List<Movie> movies = movieService.getAllMovies();
            movieTableView.setItems(FXCollections.observableArrayList(movies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFavorites () {
        try {
            List<Favorite> fav = favoriteService.getFavoritesByUserId(currentUser.getId());
            ObservableList<Favorite> favList = FXCollections.observableArrayList(fav);
            favoriteTableView.setItems(favList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupMovieTable () {
        movieGenreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        movieRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("ritle"));
    }

    private void setupFavoriteTable () {
        favoriteGenreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        favoriteRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        favoriteTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

}
