package com.example.streamingplatformfeedback.service;

import com.example.streamingplatformfeedback.model.Favorite;
import com.example.streamingplatformfeedback.repository.FavoriteRepository;

import java.util.List;

public class FavoriteService {
    private final FavoriteRepository favoriteRepo;

    public FavoriteService(FavoriteRepository repo) {
        this.favoriteRepo = repo;
    }

    public List<Favorite> getAllFavorites() {
        return favoriteRepo.findAll();
    }

    public List<Favorite> getFavoritesByUserId(Integer userId) {
        return favoriteRepo.findByUserId(userId);
    }

    public List<Favorite> addToFavorites(int userId, int movieId){
        return favoriteRepo.addToFavorites(userId, movieId);
    }

    public List<Favorite> removeFromFavorites(int userId, int movieId){
        return favoriteRepo.removeFromFavorites(userId, movieId);
    }



}