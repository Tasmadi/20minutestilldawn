package com.tilldawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controllers.ProfileMenuController;
import com.tilldawn.Controllers.SignUpMenuController;
import com.tilldawn.Models.App;
import com.tilldawn.Models.User;
import com.tilldawn.Models.GameAssetManager;

public class ProfileMenuView implements Screen {
    private final ProfileMenuController controller;
    private final Stage stage;
    private final Skin skin;
    private final User user = App.currentUser;
    private Image background;
    private TextField usernameField;
    private TextField passwordField;
    private Label errorLabel;
    private Image avatarImage;

    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.controller.setView(this);

        setupUI();
    }

    private void setupUI() {
        background = GameAssetManager.getGameAssetManager().getProfileMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(120);

        Label titleLabel = new Label("", skin);
        titleLabel.setFontScale(2.0f);
        titleLabel.setColor(Color.GOLDENROD);
        mainTable.add(titleLabel).colspan(2).padBottom(30).row();

        Table usernameTable = new Table();
        usernameTable.defaults().pad(5);

        usernameTable.add(new Label("Username:", skin)).left().width(150);
        usernameField = new TextField(user.getUsername(), skin);
        usernameTable.add(usernameField).width(300);

        TextButton changeUsernameButton = new TextButton("Change", skin);
        usernameTable.add(changeUsernameButton);

        mainTable.add(usernameTable).colspan(2).padBottom(15).row();

        Table passwordTable = new Table();
        passwordTable.defaults().pad(5);

        passwordTable.add(new Label("Password:", skin)).left().width(150);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('•');
        passwordTable.add(passwordField).width(300);

        TextButton changePasswordButton = new TextButton("Change", skin);
        passwordTable.add(changePasswordButton);

        mainTable.add(passwordTable).colspan(2).padBottom(30).row();

        Table avatarTable = new Table();
        avatarImage = new Image(new Texture(Gdx.files.internal("avatars/avatar_" + user.getAvatarId() + ".png")));
        avatarImage.setScaling(Scaling.fit);
        avatarTable.add(avatarImage).size(200).padRight(30);

        TextButton changeAvatarButton = new TextButton("Change Avatar", skin);
        avatarTable.add(changeAvatarButton).padLeft(30);

        mainTable.add(avatarTable).colspan(2).padBottom(30).row();

        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
        mainTable.add(errorLabel).colspan(2).padBottom(40).row();

        mainTable.add().colspan(2).padBottom(50).row();

        TextButton deleteAccountButton = new TextButton("Delete Account", skin);
        mainTable.add(deleteAccountButton);
        TextButton backButton = new TextButton("BACK", skin);
        mainTable.add(backButton);

        stage.addActor(mainTable);
        addListeners(backButton, changeUsernameButton, changePasswordButton, changeAvatarButton, deleteAccountButton);
    }


    private void addListeners(TextButton backButton, TextButton changeUsernameButton,
                              TextButton changePasswordButton, TextButton changeAvatarButton,
                              TextButton deleteAccountButton) {
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleBackButton();
            }
        });

        changeUsernameButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                String newUsername = usernameField.getText();
                if (newUsername.isEmpty()) {
                    showError("Username cannot be empty!");
                }
                else if (newUsername.equals(user.getUsername())) {
                    showError("New username is the same as current!");
                }
                else {
                    controller.handleChangeUsername(newUsername);
                }
            }
        });

        changePasswordButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                String newPassword = passwordField.getText();
                if (newPassword.isEmpty()) {
                    showError("Password cannot be empty!");
                }
                else if (newPassword.length() < 8) {
                    showError("Password must be at least 8 characters!");
                }
                else {
                    controller.handleChangePassword(newPassword);
                }
            }
        });

        changeAvatarButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleChangeAvatarButton();
            }
        });

        deleteAccountButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleDeleteAccountButton();
            }
        });
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    public void clearError() {
        errorLabel.setText("");
    }

    public void updateAvatar(int avatarId) {
        avatarImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
            new Texture(Gdx.files.internal("avatars/avatar_" + avatarId + ".png")))));
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
    }

    @Override public void show() { Gdx.input.setInputProcessor(stage); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() { stage.dispose(); }
}
