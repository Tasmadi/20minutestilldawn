package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;
import com.tilldawn.Views.ForgotPasswordMenuView;
import com.tilldawn.Views.WelcomeMenuView;

public class ForgotPasswordMenuController {
    private ForgotPasswordMenuView view;

    public void setView(ForgotPasswordMenuView view) {
        this.view = view;
    }

    public void handleCheckUsername(String username) {
        if (username == null || username.isEmpty()) {
            view.showError("Username cannot be empty.");
            return;
        }

        User user = App.getUserByUsername(username);
        if (user == null) {
            view.showError("Username does not exist.");
        }
        else {
            view.showQuestion(user.getSecurityQuestion());
        }
    }

    public void handleCheckAnswer(String username, String answer) {
        if (answer == null || answer.isEmpty()) {
            view.showError("Answer cannot be empty.");
            return;
        }

        User user = App.getUserByUsername(username);
        if (user == null) {
            view.showError("User not found.");
            return;
        }

        if (!user.getSecurityAnswer().equals(answer)) {
            view.showError("Incorrect answer.");
        }
        else {
            view.showPassword(user.getPassword());
        }
    }

    public void handleBackButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new WelcomeMenuView(new WelcomeMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
}
