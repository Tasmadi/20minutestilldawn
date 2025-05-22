package com.tilldawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controllers.LoginMenuController;
import com.tilldawn.Models.GameAssetManager;

public class LoginMenuView implements Screen {
    private final LoginMenuController controller;
    private final Stage stage;
    private final Skin skin;
    private TextField usernameField;
    private TextField passwordField;
    private Label errorLabel;
    private Image background;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());

        setupUI();
    }

    private void setupUI() {
        background = GameAssetManager.getGameAssetManager().getLoginMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(100);

        Label title = new Label("", skin, "title");
        title.setFontScale(1.5f);
        mainTable.add(title).colspan(2).padBottom(50).row();

        usernameField = createLargeTextField("Enter username");
        mainTable.add(new Label("Username:", skin)).padRight(20).right();
        mainTable.add(usernameField).width(400).height(80).padBottom(20).row();

        passwordField = createLargeTextField("Enter password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('•');
        mainTable.add(new Label("Password:", skin)).padRight(20).right();
        mainTable.add(passwordField).width(400).height(80).padBottom(20).row();

        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
        errorLabel.setFontScale(1.2f);
        mainTable.add(errorLabel).colspan(2).width(400).padBottom(30).row();

        Table buttonTable = new Table();

        mainTable.add().colspan(2).padBottom(300).row();

        TextButton startButton = new TextButton("START", skin);
        buttonTable.add(startButton);
        TextButton forgotPasswordButton = new TextButton("FORGOT PASSWORD", skin);
        buttonTable.add(forgotPasswordButton).pad(20);
        TextButton backButton = new TextButton("BACK", skin);
        buttonTable.add(backButton);
        mainTable.add(buttonTable).colspan(2).padTop(30);

        stage.addActor(mainTable);
        addListeners(startButton, backButton, forgotPasswordButton);
        stage.setKeyboardFocus(usernameField);
    }

    public void showError(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    private TextField createLargeTextField(String hint) {
        TextField textField = new TextField("", skin);
        textField.setMessageText(hint);
        textField.getStyle().font.getData().setScale(1.2f);
        return textField;
    }

    private void addListeners(TextButton startButton, TextButton backButton, TextButton forgotPasswordButton) {
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean success = controller.registerUser(
                    usernameField.getText(),
                    passwordField.getText()
                );

                if (success) {
                    controller.handleStartButton();
                }
            }
        });

        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleBackButton();
            }
        });

        forgotPasswordButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleForgotPasswordButton();
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
