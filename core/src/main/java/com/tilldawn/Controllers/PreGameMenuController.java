package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;
import com.tilldawn.Views.ChooseHeroMenuView;
import com.tilldawn.Views.MainMenuView;
import com.tilldawn.Views.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;
    private final User user = App.currentUser;

    public void setView(PreGameMenuView view) {
        this.view = view;
    }

    public void handleStartGame() {

    }

    public void handleChangeHero() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ChooseHeroMenuView(new ChooseHeroMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleBackButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
}
