package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;
import com.tilldawn.Views.ForgotPasswordMenuView;
import com.tilldawn.Views.LoginMenuView;
import com.tilldawn.Views.MainMenuView;
import com.tilldawn.Views.WelcomeMenuView;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public boolean registerUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            view.showError("Username and password are required!");
            return false;
        }

        User user = App.getUserByUsername(username);
        if (user == null) {
            view.showError("Username not found!");
            return false;
        }

        if (!user.getPassword().equals(password)) {
            view.showError("Incorrect password!");
            return false;
        }

        App.setCurrentUser(user);
        return true;
    }

    public void handleBackButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new WelcomeMenuView(new WelcomeMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleForgotPasswordButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ForgotPasswordMenuView(new ForgotPasswordMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleStartButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

}
