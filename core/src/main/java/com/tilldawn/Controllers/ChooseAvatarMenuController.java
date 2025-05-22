package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;
import com.tilldawn.Views.ChooseAvatarMenuView;
import com.tilldawn.Views.MainMenuView;
import com.tilldawn.Views.ProfileMenuView;

public class ChooseAvatarMenuController {
    private ChooseAvatarMenuView view;
    private final User user = App.currentUser;

    public void setView(ChooseAvatarMenuView view) {
        this.view = view;
    }

    public void handleConfirmButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
}
