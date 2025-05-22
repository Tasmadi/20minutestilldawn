package com.tilldawn.Models;

import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.Enums.Weapon;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {
    private String username;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private int id;
    private int avatarId;
    private int selectedTime;
    private int score;
    private int highScore;
    private int totalKills;
    private long longestSurvivalTime;
    private int gamesPlayed;
    private int gamesWon;
    private Hero selectedCharacter;
    private Weapon selectedWeapon;
    private String controlScheme = "WASD";
//    private GameSave currentGameSave;

    public User(String username, String password, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.avatarId = generateRandomAvatarId();
        this.score = 0;
        this.highScore = 0;
        this.totalKills = 0;
        this.longestSurvivalTime = 0;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.selectedTime = 5;
        this.selectedCharacter = Hero.SHANA;
        this.selectedWeapon = Weapon.REVOLVER;
    }

    public String getControlScheme() {
        return controlScheme;
    }

    public void setControlScheme(String controlScheme) {
        this.controlScheme = controlScheme;
    }


    private int generateRandomAvatarId() {
        Random random = new Random();
        return random.nextInt(30) + 1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        if (score > highScore) {
            highScore = score;
        }
    }

    public int getHighScore() {
        return highScore;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void addKills(int kills) {
        this.totalKills += kills;
    }

    public long getLongestSurvivalTime() {
        return longestSurvivalTime;
    }

    public void updateSurvivalTime(long time) {
        if (time > longestSurvivalTime) {
            longestSurvivalTime = time;
        }
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void incrementGamesWon() {
        this.gamesWon++;
    }

    public Hero getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(Hero selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    public Weapon getSelectedWeapon() {
        return selectedWeapon;
    }

    public void setSelectedWeapon(Weapon selectedWeapon) {
        this.selectedWeapon = selectedWeapon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //    public GameSave getCurrentGameSave() {
//        return currentGameSave;
//    }
//
//    public void setCurrentGameSave(GameSave currentGameSave) {
//        this.currentGameSave = currentGameSave;
//    }
//
//    public void saveGame(GameState gameState) {
//        this.currentGameSave = new GameSave(gameState);
//    }

//    public void clearGameSave() {
//        this.currentGameSave = null;
//    }
//
//    public boolean hasSavedGame() {
//        return currentGameSave != null;
//    }

    public int getSelectedTime() {
        return selectedTime;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public void setLongestSurvivalTime(long longestSurvivalTime) {
        this.longestSurvivalTime = longestSurvivalTime;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setSelectedTime(int selectedTime) {
        this.selectedTime = selectedTime;
    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            ", score=" + score +
            ", highScore=" + highScore +
            ", totalKills=" + totalKills +
            ", longestSurvivalTime=" + longestSurvivalTime +
            ", gamesPlayed=" + gamesPlayed +
            ", gamesWon=" + gamesWon +
            '}';
    }
}
