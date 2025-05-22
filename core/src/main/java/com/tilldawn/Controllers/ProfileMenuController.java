package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;
import com.tilldawn.Views.ChooseAvatarMenuView;
import com.tilldawn.Views.MainMenuView;
import com.tilldawn.Views.ProfileMenuView;

public class ProfileMenuController {
    private ProfileMenuView view;
    private final User user = App.currentUser;

    public ProfileMenuController() {
    }

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleBackButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleChangeUsername(String newUsername) {
        if (isUsernameTaken(newUsername)) {
            view.showError("Username already taken!");
        }
        else {
            user.setUsername(newUsername);
            view.clearError();
        }
    }

    public void handleChangePassword(String newPassword) {
        if (isPasswordWeak(newPassword)) {
            view.showError("Password is too weak! Use numbers and special characters.");
        }
        else {
            user.setPassword(newPassword);
            view.clearError();
        }
    }

    public void handleChangeAvatarButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ChooseAvatarMenuView(new ChooseAvatarMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleDeleteAccountButton() {

    }

    private boolean isUsernameTaken(String username) {
        return false;
    }

    private boolean isPasswordWeak(String password) {
        return password.length() < 8 ||
            !password.matches(".*\\d.*") ||
            !password.matches(".*[!@#$%^&*()].*");
    }
}
