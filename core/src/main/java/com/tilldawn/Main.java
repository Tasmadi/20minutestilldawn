package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Controllers.WelcomeMenuController;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Views.WelcomeMenuView;

public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;

    public void create() {
        main = this;
        batch = new SpriteBatch();
        getMain().setScreen(new WelcomeMenuView(new WelcomeMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }
}
