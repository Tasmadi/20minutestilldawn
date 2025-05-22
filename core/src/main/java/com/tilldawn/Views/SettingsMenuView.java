package com.tilldawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controllers.SettingsMenuController;
import com.tilldawn.Models.GameAssetManager;

public class SettingsMenuView implements Screen {
    private final SettingsMenuController controller;
    private final Stage stage;
    private final Skin skin;
    private Image background;

    public SettingsMenuView(SettingsMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.controller.setView(this);

        setupUI();
    }

    private void setupUI() {
        background = GameAssetManager.getGameAssetManager().getSettingMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(100);

        Label titleLabel = new Label("", skin);
        titleLabel.setFontScale(2.0f);
        titleLabel.setColor(Color.GOLDENROD);
        mainTable.add(titleLabel).colspan(3).padBottom(50).row();

        Table audioSettingsTable = new Table();
        audioSettingsTable.defaults().pad(10);

        audioSettingsTable.add(createVolumeSetting("Music Volume", 0.7f)).width(400);
        audioSettingsTable.add(createTrackSetting("Current Track")).width(400);
        audioSettingsTable.add(createVolumeSetting("SFX Volume", 0.8f)).width(400);
        mainTable.add(audioSettingsTable).colspan(3).padBottom(15).row();

        addToggleSetting(mainTable, "Auto Reload", true);
        addToggleSetting(mainTable, "Grayscale Mode", false);

        Table controlTable = new Table();
        controlTable.defaults().pad(10);

        Label controlLabel = new Label("Control Scheme:", skin);
        controlLabel.setFontScale(1.3f);
        controlTable.add(controlLabel).left().width(250);

        SelectBox<String> controlSelectBox = new SelectBox<>(skin);
        controlSelectBox.setItems("WASD", "Arrow Keys");
        controlSelectBox.setSelected(controller.getCurrentControlScheme());
        controlTable.add(controlSelectBox).left().width(300);

        mainTable.add(controlTable).colspan(3).padTop(30).row();

        mainTable.add().colspan(2).padBottom(75).row();

        TextButton backButton = new TextButton("BACK", skin);
        mainTable.add(backButton).colspan(3).width(250).height(120).padTop(50);

        stage.addActor(mainTable);
        addListeners(backButton, controlSelectBox);
    }

    private Table createVolumeSetting(String title, float defaultValue) {
        Table settingTable = new Table();
        settingTable.defaults().pad(5);

        Label titleLabel = new Label(title, skin);
        titleLabel.setFontScale(1.2f);
        settingTable.add(titleLabel).colspan(2).center().padBottom(5).row();

        Slider slider = new Slider(0, 100, 1, false, skin);
        slider.setValue(defaultValue * 100);
        settingTable.add(slider).width(400).row();

        Label valueLabel = new Label(String.valueOf((int)(defaultValue * 100)), skin);
        valueLabel.setFontScale(1.1f);
        settingTable.add(valueLabel);

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                valueLabel.setText(String.valueOf((int)slider.getValue()));
            }
        });

        return settingTable;
    }

    private Table createTrackSetting(String title) {
        Table settingTable = new Table();
        settingTable.defaults().pad(5);

        Label titleLabel = new Label(title, skin);
        titleLabel.setFontScale(1.2f);
        settingTable.add(titleLabel).center().padBottom(5).row();

        String[] tracks = {"Track 1", "Track 2", "Track 3", "Track 4", "Track 5"};
        SelectBox<String> trackSelectBox = new SelectBox<>(skin);
        trackSelectBox.setItems(tracks);
        trackSelectBox.setSelectedIndex(0);
        settingTable.add(trackSelectBox).width(300).row();

        settingTable.add(new Label("", skin)).height(20);

        trackSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleTrackSelection(trackSelectBox.getSelected());
            }
        });

        return settingTable;
    }

    private void addToggleSetting(Table parent, String title, boolean defaultValue) {
        Table settingTable = new Table();
        settingTable.defaults().pad(10);

        Label titleLabel = new Label(title, skin);
        titleLabel.setFontScale(1.3f);
        settingTable.add(titleLabel).left().width(250);

        CheckBox toggle = new CheckBox("", skin);
        toggle.setChecked(defaultValue);
        settingTable.add(toggle).left();

        parent.add(settingTable).fillX().padBottom(15).colspan(3).row();
    }

    private void addListeners(TextButton backButton, SelectBox<String> controlSelectBox) {
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleBackButton();
            }
        });

        controlSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleControlSchemeChange(controlSelectBox.getSelected());
            }
        });

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
