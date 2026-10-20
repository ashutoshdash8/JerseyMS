package com.aip.jersey.dao;

import com.aip.jersey.model.Jersey;
import com.aip.jersey.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JerseyDAO {

    private Jersey map(ResultSet rs) throws SQLException {
        return new Jersey(
                rs.getInt("id"),
                rs.getString("team_name"),
                rs.getString("player_name"),
                rs.getInt("jersey_number"),
                rs.getString("size"),
                rs.getDouble("price"),
                rs.getInt("quantity"));
    }

    public List<Jersey> getAll() throws SQLException {
        List<Jersey> list = new ArrayList<>();
        String sql = "SELECT * FROM jerseys ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public List<Jersey> search(String keyword) throws SQLException {
        List<Jersey> list = new ArrayList<>();
        String sql = "SELECT * FROM jerseys WHERE team_name LIKE ? OR player_name LIKE ? ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }

    public Jersey getById(int id) throws SQLException {
        String sql = "SELECT * FROM jerseys WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    public void add(Jersey j) throws SQLException {
        String sql = "INSERT INTO jerseys (team_name, player_name, jersey_number, size, price, quantity) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, j.getTeamName());
            ps.setString(2, j.getPlayerName());
            ps.setInt(3, j.getJerseyNumber());
            ps.setString(4, j.getSize());
            ps.setDouble(5, j.getPrice());
            ps.setInt(6, j.getQuantity());
            ps.executeUpdate();
        }
    }

    public void update(Jersey j) throws SQLException {
        String sql = "UPDATE jerseys SET team_name=?, player_name=?, jersey_number=?, size=?, price=?, quantity=? "
                + "WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, j.getTeamName());
            ps.setString(2, j.getPlayerName());
            ps.setInt(3, j.getJerseyNumber());
            ps.setString(4, j.getSize());
            ps.setDouble(5, j.getPrice());
            ps.setInt(6, j.getQuantity());
            ps.setInt(7, j.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM jerseys WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}