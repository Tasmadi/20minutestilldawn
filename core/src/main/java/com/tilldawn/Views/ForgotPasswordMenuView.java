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
import com.tilldawn.Controllers.ForgotPasswordMenuController;
import com.tilldawn.Models.GameAssetManager;

public class ForgotPasswordMenuView implements Screen {
    private final ForgotPasswordMenuController controller;
    private final Stage stage;
    private final Skin skin;

    private TextField usernameField;
    private Label questionLabel;
    private TextField answerField;
    private Label passwordLabel;
    private Label errorLabel;
    private Image background;

    public ForgotPasswordMenuView(ForgotPasswordMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.controller.setView(this);
        setupUI();
    }

    private void setupUI() {
        background = GameAssetManager.getGameAssetManager().getSignUpMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center().padTop(320);

        usernameField = createLargeTextField("Enter username");
        TextButton checkButton = new TextButton("Check", skin);
        Label usernameLabel = new Label("Username:", skin);

        Table userRow = new Table();
        userRow.add(usernameLabel).padRight(20).right();
        userRow.add(usernameField).width(300).height(80).padRight(10);
        userRow.add(checkButton);
        mainTable.add(userRow).center().padBottom(20).row();

        questionLabel = new Label("", skin);
        questionLabel.setFontScale(1.5f);
        mainTable.add(questionLabel).center().padBottom(20).row();

        answerField = createLargeTextField("Enter your answer");
        TextButton getPasswordButton = new TextButton("Get Password", skin);
        Label answerLabel = new Label("Answer:", skin);

        Table answerRow = new Table();
        answerRow.add(answerLabel).padRight(20).right();
        answerRow.add(answerField).width(300).height(80).padRight(10);
        answerRow.add(getPasswordButton);
        mainTable.add(answerRow).center().padBottom(20).row();

        passwordLabel = new Label("", skin);
        passwordLabel.setFontScale(2f);
        mainTable.add(passwordLabel).center().padBottom(20).row();

        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
        errorLabel.setFontScale(1.2f);
        mainTable.add(errorLabel).width(400).center().padBottom(30).row();

        mainTable.add().colspan(2).padBottom(-80).row();

        TextButton backButton = new TextButton("BACK", skin);
        mainTable.add(backButton).center().padTop(20);

        stage.addActor(mainTable);
        addListeners(checkButton, getPasswordButton, backButton);
    }


    private TextField createLargeTextField(String hint) {
        TextField textField = new TextField("", skin);
        textField.setMessageText(hint);
        textField.getStyle().font.getData().setScale(1.2f);
        return textField;
    }

    private void addListeners(TextButton checkButton, TextButton getPasswordButton, TextButton backButton) {
        checkButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleCheckUsername(usernameField.getText());
            }
        });

        getPasswordButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleCheckAnswer(usernameField.getText(), answerField.getText());
            }
        });

        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleBackButton();
            }
        });
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    public void showQuestion(String question) {
        questionLabel.setText(question);
        errorLabel.setText("");
    }

    public void showPassword(String password) {
        passwordLabel.setText("Password: " + password);
        errorLabel.setText("");
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        background.setSize(width, height);
    }

    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        stage.dispose();
    }
}
