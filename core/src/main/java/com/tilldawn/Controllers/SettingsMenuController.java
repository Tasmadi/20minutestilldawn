package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;
import com.tilldawn.Views.MainMenuView;
import com.tilldawn.Views.SettingsMenuView;
import com.tilldawn.Views.WelcomeMenuView;

public class SettingsMenuController {
    private SettingsMenuView view;
    private final User user = App.currentUser;

    public void setView(SettingsMenuView view) {
        this.view = view;
    }

    public void handleBackButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleTrackSelection(String selectedTrack) {

    }

    public void handleControlSchemeChange(String selectedScheme) {
        user.setControlScheme(selectedScheme);
    }

    public String getCurrentControlScheme() {
        return user.getControlScheme();
    }


}
