package com.example.streamingplatformfeedback.repository;

import com.example.streamingplatformfeedback.infrastructure.DbConfig;
import com.example.streamingplatformfeedback.model.User;
import com.example.streamingplatformfeedback.repository.SqlRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final DbConfig db;

    public UserRepository(DbConfig db) {
        this.db = db;
    }

    public List<User> findAll() {
        String sql = "Select id, email, name from users";

        List<User> result = new ArrayList<>();

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

    public List<User> findByEmail(String email){
        String sql = "select id, email, name from users where email = ?";
        List<User> result = new ArrayList<>();
        try(Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, email);

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    result.add(mapRow(rs));
                }
            }
            return result;

        } catch (Exception e){
            throw new DataAccessException("Fejl ved findByEmail()", e);
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("name")
        );
    }


}
