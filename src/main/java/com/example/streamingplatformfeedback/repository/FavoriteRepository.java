package com.example.streamingplatformfeedback.repository;

import com.example.streamingplatformfeedback.infrastructure.DbConfig;
import com.example.streamingplatformfeedback.model.Favorite;
import com.example.streamingplatformfeedback.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteRepository {
    private final DbConfig db;

    public FavoriteRepository(DbConfig db) {
        this.db = db;
    }

    public List<Favorite> findAll() {
        String sql = "Select id, title, rating, genre from favorites";

        List<Favorite> result = new ArrayList<>();

        try(Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(mapRow(rs));
            }
            return result;

        } catch (Exception e){
            throw new DataAccessException("Fejl ved findAll()", e);
        }
    }

    public List<Favorite> addToFavorites(int userId, int movieId){
        String sql = "INSERT INTO favorites (userid, movieid) VALUES (?, ?)";
        List<Favorite> result = new ArrayList<>();
        try(Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, userId);
            ps.setInt(2, movieId);

            ps.executeUpdate();
            return result;

        } catch (Exception e){
            throw new DataAccessException("Fejl ved addToFavorites()", e);
        }
    }

    public List<Favorite> removeFromFavorites(int userId, int movieId){
        String sql = "DELETE FROM favorites WHERE userid = ? AND movieid = ?";
        List<Favorite> result = new ArrayList<>();
        try(Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, movieId);
            ps.executeUpdate();
            return result;
        } catch (Exception e){
            throw new DataAccessException("Fejl ved removeFromFavorites()", e);
        }
    }

    public List<Favorite> findByUserId(int userId){
        String sql = "select id, userid, movieid from favorites where userid = ?";
        List<Favorite> result = new ArrayList<>();
        try(Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, userId);

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    result.add(mapRow(rs));
                }
            }
            return result;

        } catch (Exception e){
            throw new DataAccessException("Fejl ved findByUserId()", e);
        }
    }

    private Favorite mapRow(ResultSet rs) throws SQLException {
        return new Favorite(
                rs.getInt("id"),
                rs.getInt("userid"),
                rs.getInt("movieid")
        );
    }

}
