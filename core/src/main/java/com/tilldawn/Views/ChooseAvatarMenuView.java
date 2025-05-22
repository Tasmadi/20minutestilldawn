package com.tilldawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controllers.ChooseAvatarMenuController;
import com.tilldawn.Controllers.ProfileMenuController;
import com.tilldawn.Controllers.SignUpMenuController;
import com.tilldawn.Models.App;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;

import javax.swing.event.ChangeListener;

public class ChooseAvatarMenuView implements Screen {
    private final ChooseAvatarMenuController controller;
    private final Stage stage;
    private final Skin skin;
    private final User user = App.currentUser;
    private Image background;

    public ChooseAvatarMenuView(ChooseAvatarMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.controller.setView(this);

        setupUI();
    }

    private Image selectedAvatarImage = null;

    public void setupUI() {
        background = GameAssetManager.getGameAssetManager().getChooseAvatarMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table avatarSelectionTable = new Table();
        avatarSelectionTable.setFillParent(true);
        avatarSelectionTable.center().padTop(130);

        int currentAvatarId = user.getAvatarId();
        int avatarType = (currentAvatarId - 1) / 5 + 1;

        for (int i = 1; i <= 5; i++) {
            int avatarId = ((avatarType - 1) * 5) + i;
            Image avatarImage = GameAssetManager.getAvatarImage(avatarId);
            avatarImage.setSize(200, 200);

            final int selectedAvatarId = avatarId;
            avatarImage.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    user.setAvatarId(selectedAvatarId);

                    if (selectedAvatarImage != null) {
                        selectedAvatarImage.setColor(Color.WHITE);
                    }

                    selectedAvatarImage = avatarImage;
                    selectedAvatarImage.setColor(Color.GOLD);
                }
            });
            avatarSelectionTable.add(avatarImage).size(200).pad(10);
        }

        avatarSelectionTable.add().colspan(2).padBottom(650).row();

        TextButton confirmButton = new TextButton("Confirm", skin);
        confirmButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                controller.handleConfirmButton();
            }
        });

        avatarSelectionTable.row().padTop(20);
        avatarSelectionTable.add(confirmButton).colspan(5).center();

        stage.addActor(avatarSelectionTable);
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
