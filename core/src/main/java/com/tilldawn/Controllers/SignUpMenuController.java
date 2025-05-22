package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;
import com.tilldawn.Views.MainMenuView;
import com.tilldawn.Views.SignUpMenuView;
import com.tilldawn.Views.WelcomeMenuView;
import java.util.Random;

public class SignUpMenuController {
    private SignUpMenuView view;
//    private UserManager userManager;

//    public SignUpMenuController() {
//        this.userManager = UserManager.getUserManager();
//    }

    public void setView(SignUpMenuView view) {
        this.view = view;
    }

    private boolean isLengthValid(String password) {
        return password.length() >= 8;
    }

    private boolean containsSpecialCharacter(String password) {
        return password.matches(".*[@%$#&*()_].*");
    }

    private boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean hasUpperCaseLetter(String password) {
        return password.matches(".*[A-Z].*");
    }

    private String[] securityQuestions = {
        "What was your first pet's name?",
        "What city were you born in?",
        "What is your mother's maiden name?",
        "What was the name of your first school?",
        "What was your favorite childhood toy?"
    };

    public String[] getSecurityQuestions() {
        return securityQuestions;
    }

    public boolean registerUser(String username, String password, String confirmPassword,
                                String securityQuestion, String securityAnswer) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
            securityQuestion.isEmpty() || securityAnswer.isEmpty()) {
            view.showError("All fields are required!");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            view.showError("Passwords don't match!");
            return false;
        }

        if (!isLengthValid(password)) {
            view.showError("Password is too short.");
            return false;
        }

        if (!containsSpecialCharacter(password)) {
            view.showError("Password must contain at least one special character.");
            return false;
        }

        if (!containsDigit(password)) {
            view.showError("Password must contain at least one digit.");
            return false;
        }

        if (!hasUpperCaseLetter(password)) {
            view.showError("Password must contain at least one uppercase letter.");
            return false;
        }

        if (App.userExists(username)) {
            view.showError("Username already exists!");
            return false;
        }

        User user = new User(username, password, securityQuestion, securityAnswer);
        App.addUser(user);
        App.getUserDAO().saveUser(user);

        return true;
    }

    public void handleBackButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new WelcomeMenuView(new WelcomeMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handlePlayAsGuestButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

//    public void handleRegisterButton() {
//        Main.getMain().getScreen().dispose();
//        Main.getMain().setScreen(new WelcomeMenuView(new WelcomeMenuController(),
//            GameAssetManager.getGameAssetManager().getSkin()));
//    }

    public void handleRegisterButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
}
