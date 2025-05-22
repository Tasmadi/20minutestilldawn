package com.tilldawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controllers.WelcomeMenuController;
import com.tilldawn.Models.GameAssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class WelcomeMenuView implements Screen {
    private TextButton signUpButton;
    private TextButton loginButton;
    private final WelcomeMenuController controller;
    private final Stage stage;
    private final Skin skin;
    private Image background;

    public WelcomeMenuView(WelcomeMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());

        controller.setView(this);
        setupUI();
    }

    private void setupUI() {
        background = GameAssetManager.getGameAssetManager().getWelcomeMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table table = new Table();
        table.setFillParent(true);
        table.bottom().padBottom(320);

        signUpButton = new TextButton("SIGN UP", skin);
        loginButton = new TextButton("LOG IN", skin);
        table.add(signUpButton).pad(10);
        table.add(loginButton).pad(10);

        stage.addActor(table);

        loginButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleLoginButton();
            }
        });

        signUpButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleSignUpButton();
            }
        });
    }

    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void pause() {}

    public void resume() {}

    public void hide() {}

    public void dispose() {
        stage.dispose();
    }

    public TextButton getSignUpButton() {
        return signUpButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public WelcomeMenuController getController() {
        return controller;
    }

    public Image getBackground() {
        return background;
    }
}
