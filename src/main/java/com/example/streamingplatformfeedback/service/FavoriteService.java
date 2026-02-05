package com.example.streamingplatformfeedback.service;

import com.example.streamingplatformfeedback.model.Favorite;
import com.example.streamingplatformfeedback.repository.FavoriteRepository;

import java.util.List;

public class FavoriteService {
    private final FavoriteRepository favoriteRepo;

    public FavoriteService(FavoriteRepository repo) {
        this.favoriteRepo = repo;
    }


    public List<Favorite> getFavoritesByUserId(Integer userId) {
        return favoriteRepo.findAllByUserId(userId);
    }

    public void addToFavorites(int userId, int movieId) {
        favoriteRepo.addToFavorites(userId, movieId);
    }

    public void removeFromFavorites(int userId, int movieId) {
        favoriteRepo.removeFromFavorites(userId, movieId);
    }
}



