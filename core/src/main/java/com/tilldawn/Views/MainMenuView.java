package com.tilldawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controllers.MainMenuController;
import com.tilldawn.Controllers.SignUpMenuController;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;

public class MainMenuView implements Screen {
    private final MainMenuController controller;
    private final Stage stage;
    private final Skin skin;
    private Image background;
    private Image avatarImage;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.controller.setView(this);

        setupUI();
    }

    private void setupUI() {
        background = GameAssetManager.getGameAssetManager().getMainMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(50);

        Table buttonsTable = new Table();
        buttonsTable.top().padTop(50).left();

        Table rightTable = new Table();
        rightTable.top().padTop(50).right();

        Table userInfoTable = new Table();
        userInfoTable.top().right();

        avatarImage = GameAssetManager.getGameAssetManager().getAvatarImage(App.currentUser.getAvatarId());
        userInfoTable.add(avatarImage).size(300, 300).row();

        Label usernameLabel = new Label(App.currentUser.getUsername(), skin);
        usernameLabel.setFontScale(2.6f);
        userInfoTable.add(usernameLabel).padTop(10).row();

        Label scoreLabel = new Label("Score: " + App.currentUser.getScore(), skin);
        scoreLabel.setFontScale(2.1f);
        userInfoTable.add(scoreLabel).padTop(5);

        rightTable.add(userInfoTable).right().padRight(50);

        TextButton[] menuButtons = {
            createMenuButton("SETTINGS"),
            createMenuButton("PROFILE"),
            createMenuButton("GAME PREPARATION"),
            createMenuButton("SCOREBOARD"),
            createMenuButton("HINT"),
            createMenuButton("CONTINUE GAME"),
            createMenuButton("LOGOUT")
        };

        for (TextButton button : menuButtons) {
            buttonsTable.add(button).width(660).height(110).padBottom(15).left().row();
        }

        mainTable.add(buttonsTable).expandY().left().width(Gdx.graphics.getWidth() * 0.4f).padLeft(200);
        mainTable.add().expandX();
        mainTable.add(rightTable).expandY().right().width(Gdx.graphics.getWidth() * 0.4f).padRight(130);

        stage.addActor(mainTable);
        addListeners(menuButtons);
    }

    private TextButton createMenuButton(String text) {
        TextButton button = new TextButton(text, skin);
        button.getLabel().setFontScale(1.2f);
        return button;
    }

    private void addListeners(TextButton... buttons) {
        buttons[0].addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleSettings();
            }
        });
//
        buttons[1].addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleProfile();
            }
        });

        buttons[2].addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handlePreGame();
            }
        });
//
//        buttons[3].addListener(new ChangeListener() {
//            public void changed(ChangeEvent event, Actor actor) {
//                controller.handleScoreboard();
//            }
//        });
//
        buttons[4].addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleHint();
            }
        });

        buttons[5].addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleContinueGame();
            }
        });

        buttons[6].addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleLogout();
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
        background.setSize(width, height);
    }

    public void pause() {}

    public void resume() {}

    public void hide() {}

    public void dispose() {
        stage.dispose();
    }
}
