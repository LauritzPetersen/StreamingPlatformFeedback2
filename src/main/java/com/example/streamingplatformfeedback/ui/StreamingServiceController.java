package com.example.streamingplatformfeedback.ui;

import com.example.streamingplatformfeedback.model.Favorite;
import com.example.streamingplatformfeedback.model.Movie;
import com.example.streamingplatformfeedback.model.User;
import com.example.streamingplatformfeedback.service.FavoriteService;
import com.example.streamingplatformfeedback.service.MovieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class StreamingServiceController {

    private User currentUser;
    private MovieService movieService;
    private FavoriteService favoriteService;

    @FXML
    public void initializeData(User user, MovieService movieService, FavoriteService favoriteService) {
        this.currentUser = user;
        this.movieService = movieService;
        this.favoriteService = favoriteService;
        setupMovieTable();
        setupFavoriteTable();
    }


    // Tableviews and columns for movies and favorites

    @FXML private TableView favoriteTableView;
    @FXML private TableColumn<Favorite, String> favoriteTitleColumn;
    @FXML private TableColumn<Favorite, Double> favoriteRatingColumn;
    @FXML private TableColumn<Favorite, String> favoriteGenreColumn;

    @FXML private TableView movieTableView;
    @FXML private TableColumn<Movie, String> movieTitleColumn;
    @FXML private TableColumn<Movie, Double> movieRatingColumn;
    @FXML private TableColumn<Movie, String> movieGenreColumn;


    @FXML private Label nameText;
    @FXML private Label emailText;


    @FXML private Button addFavorite;
    @FXML private Button removeFavorite;
    @FXML private Button logOut;



    @FXML
    private void handleAddFavorite(){
        Movie selectedMovie = (Movie) movieTableView.getSelectionModel().getSelectedItem();

        try{
            favoriteService.addToFavorites(currentUser.getId(), selectedMovie.getId());
            loadFavorites();
        } catch (Exception e){

        }
    }

    @FXML
    private void handleRemoveFavorite(){
        Favorite selectedFavorite = (Favorite) favoriteTableView.getSelectionModel().getSelectedItem();

        try{
            favoriteService.removeFromFavorites(currentUser.getId(), selectedFavorite.getMovieId());
            loadFavorites();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void loadFavorites(){
        try{
            List<Favorite> fav = favoriteService.getFavoritesByUserId(currentUser.getId());
            ObservableList<Favorite> favList = FXCollections.observableArrayList(fav);
            favoriteTableView.setItems(favList);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setupMovieTable(){
        movieGenreColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        movieRatingColumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
    }

    private void setupFavoriteTable(){
        favoriteGenreColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        favoriteRatingColumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        favoriteTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
    }

    @FXML
    private void onRefreshButtonClick() {
//        refreshTable();
    }


}
