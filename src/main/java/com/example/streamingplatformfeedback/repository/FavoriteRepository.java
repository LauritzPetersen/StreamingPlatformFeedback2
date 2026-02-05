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

    public List<Favorite> findAllByUserId(int userId){
        String sql = "SELECT f.userid, f.movieid, m.title, m.rating, m.genre " +
                "FROM favorites f " +
                "JOIN movies m ON f.movieid = m.id " +
                "WHERE f.userid = ?";

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

    public void addToFavorites(int userId, int movieId){
        String sql = "INSERT INTO favorites (userid, movieid) VALUES (?, ?)";
        List<Favorite> result = new ArrayList<>();
        try(Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, userId);
            ps.setInt(2, movieId);

            ps.executeUpdate();

        } catch (Exception e){
            throw new DataAccessException("Fejl ved addToFavorites()", e);
        }
    }

    public void removeFromFavorites(int userId, int movieId){
        String sql = "DELETE FROM favorites WHERE userid = ? AND movieid = ?";
        List<Favorite> result = new ArrayList<>();
        try(Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, movieId);

            ps.executeUpdate();

        } catch (Exception e){
            throw new DataAccessException("Fejl ved removeFromFavorites()", e);
        }
    }


    private Favorite mapRow(ResultSet rs) throws SQLException {
        return new Favorite(
                rs.getInt("userid"),
                rs.getInt("movieid"),
                rs.getString("title"),
                rs.getDouble("rating"),
                rs.getString("genre")
        );
    }

}
