package com.tilldawn.database;

import java.sql.*;

public class GameSaveDAO {

    public void saveGame(int userId, String saveDataJson) {
        String query = "INSERT INTO game_saves (user_id, save_data) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, saveDataJson);

            stmt.executeUpdate();
            System.out.println("GAME SAVED SUCCESSFULLY");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String loadLastSaveByUserId(int userId) {
        String query = "SELECT save_data FROM game_saves WHERE user_id = ? ORDER BY saved_at DESC LIMIT 1";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("save_data");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteAllSavesForUser(int userId) {
        String query = "DELETE FROM game_saves WHERE user_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

            System.out.println("ALL GAMES DELETED SUCCESSFULLY");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
