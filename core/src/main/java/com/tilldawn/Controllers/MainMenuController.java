package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;
//import com.tilldawn.Models.UserManager;
import com.tilldawn.Views.*;

public class MainMenuController {
    private MainMenuView view;
    private final User user = App.currentUser;

    public MainMenuController() {

    }

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleSettings() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new SettingsMenuView(new SettingsMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
//
    public void handleProfile() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handlePreGame() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
//
//    public void handleScoreboard() {
//        Main.getMain().getScreen().dispose();
//        Main.getMain().setScreen(new ScoreboardView(new ScoreboardController(),
//            GameAssetManager.getGameAssetManager().getSkin()));
//    }
//
    public void handleHint() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new TalentHintMenuView(new TalentHintMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleContinueGame() {

    }

    public void handleLogout() {
        App.setCurrentUser(null);

        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new WelcomeMenuView(new WelcomeMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
}
