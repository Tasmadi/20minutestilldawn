package com.tilldawn.database;

import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.Enums.Weapon;
import com.tilldawn.Models.User;
import java.sql.*;

public class UserDAO {

    public void saveUser(User user) {
        String query = "INSERT INTO users (username, password, security_question, security_answer, avatar_id, " +
            "selected_time, score, high_score, total_kills, longest_survival_time, games_played, games_won, " +
            "selected_character, selected_weapon, control_scheme) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getSecurityQuestion());
            stmt.setString(4, user.getSecurityAnswer());
            stmt.setInt(5, user.getAvatarId());
            stmt.setInt(6, user.getSelectedTime());
            stmt.setInt(7, user.getScore());
            stmt.setInt(8, user.getHighScore());
            stmt.setInt(9, user.getTotalKills());
            stmt.setLong(10, user.getLongestSurvivalTime());
            stmt.setInt(11, user.getGamesPlayed());
            stmt.setInt(12, user.getGamesWon());
            stmt.setString(13, user.getSelectedCharacter().name());
            stmt.setString(14, user.getSelectedWeapon().name());
            stmt.setString(15, user.getControlScheme());

            stmt.executeUpdate();
            System.out.println("USER SAVED SUCCESSFULLY");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("security_question"),
                    rs.getString("security_answer")
                );
                user.setId(rs.getInt("id"));
                user.setAvatarId(rs.getInt("avatar_id"));
                user.setSelectedTime(rs.getInt("selected_time"));
                user.setScore(rs.getInt("score"));
                user.setHighScore(rs.getInt("high_score"));
                user.setTotalKills(rs.getInt("total_kills"));
                user.setLongestSurvivalTime(rs.getLong("longest_survival_time"));
                user.setGamesPlayed(rs.getInt("games_played"));
                user.setGamesWon(rs.getInt("games_won"));
                user.setSelectedCharacter(Hero.valueOf(rs.getString("selected_character")));
                user.setSelectedWeapon(Weapon.valueOf(rs.getString("selected_weapon")));
                user.setControlScheme(rs.getString("control_scheme"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
