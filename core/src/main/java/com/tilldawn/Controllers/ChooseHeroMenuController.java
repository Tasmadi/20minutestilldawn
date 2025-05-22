package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;
import com.tilldawn.Views.ChooseAvatarMenuView;
import com.tilldawn.Views.ChooseHeroMenuView;
import com.tilldawn.Views.PreGameMenuView;
import com.tilldawn.Views.ProfileMenuView;

public class ChooseHeroMenuController {
    private ChooseHeroMenuView view;
    private final User user = App.currentUser;

    public void setView(ChooseHeroMenuView view) {
        this.view = view;
    }

    public void handleConfirmButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
}
