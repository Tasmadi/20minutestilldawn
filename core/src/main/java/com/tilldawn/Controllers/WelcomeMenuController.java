package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Views.LoginMenuView;
import com.tilldawn.Views.SignUpMenuView;
import com.tilldawn.Views.WelcomeMenuView;

public class WelcomeMenuController {
    private WelcomeMenuView view;

    public void setView(WelcomeMenuView view) {
        this.view = view;
    }

    public void handleLoginButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleSignUpButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new SignUpMenuView(new SignUpMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }

//    public void handleMainMenuButtons() {
//        if (view != null) {
//            if (view.getLoginButton().isPressed()) {
//                Main.getMain().getScreen().dispose();
////                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(),
////                    GameAssetManager.getGameAssetManager().getSkin()));
//            }
//            else if (view.getSignUpButton().isPressed()) {
//                Main.getMain().getScreen().dispose();
//                Main.getMain().setScreen(new SignUpMenuView(new SignUpMenuController(),
//                    GameAssetManager.getGameAssetManager().getSkin()));
//            }
//        }
//    }

}
