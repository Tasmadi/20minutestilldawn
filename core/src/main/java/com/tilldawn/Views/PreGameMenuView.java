package com.tilldawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controllers.PreGameMenuController;
import com.tilldawn.Controllers.SignUpMenuController;
import com.tilldawn.Models.App;
import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.Enums.Weapon;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;

public class PreGameMenuView implements Screen {
    private final PreGameMenuController controller;
    private final Stage stage;
    private final Skin skin;
    private final User user = App.currentUser;
    private Image background;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.controller.setView(this);

        setupUI();
    }

    public void setupUI() {
        background = GameAssetManager.getGameAssetManager().getPreGameMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center().padTop(150);

        Table heroSection = new Table();

        Image heroImage = GameAssetManager.getHeroImage(user.getSelectedCharacter());
        heroImage.setSize(150, 150);
        heroSection.add(heroImage).size(150).pad(10).row();

        Label heroName = new Label(user.getSelectedCharacter().name(), skin);
        heroSection.add(heroName).row();

        Label heroStats = new Label(
            "HP: " + user.getSelectedCharacter().getHp() +
                " | Speed: " + user.getSelectedCharacter().getSpeed(),
            skin
        );
        heroSection.add(heroStats).padBottom(10).row();

        TextButton changeHeroButton = new TextButton("Change Hero", skin);
        changeHeroButton.addListener(new ClickListener() {
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                controller.handleChangeHero();
            }
        });
        heroSection.add(changeHeroButton).padBottom(30);

        mainTable.add(heroSection).colspan(2).center().row();

        Table weaponTable = new Table();
        weaponTable.add(new Label("Select Weapon:", skin)).left().padRight(10);

        SelectBox<Weapon> weaponSelectBox = new SelectBox<>(skin);
        weaponSelectBox.setItems(Weapon.values());
        weaponSelectBox.setSelected(user.getSelectedWeapon());
        weaponSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                user.setSelectedWeapon(weaponSelectBox.getSelected());
            }
        });
        weaponTable.add(weaponSelectBox).width(400);

        mainTable.add(weaponTable).colspan(2).padBottom(20).row();

        Table timeTable = new Table();
        timeTable.add(new Label("Select Game Time (minutes):", skin)).left().padRight(10);

        SelectBox<Integer> timeSelectBox = new SelectBox<>(skin);
        timeSelectBox.setItems(2, 5, 10, 20);
        timeSelectBox.setSelected(5);
        timeTable.add(timeSelectBox).width(200);

        mainTable.add(timeTable).colspan(2).padBottom(80).row();

        mainTable.add().colspan(2).padBottom(50).row();

        Table buttonTable = new Table();

        TextButton backButton = new TextButton("BACK", skin);
        backButton.addListener(new ClickListener() {
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                controller.handleBackButton();
            }
        });
        buttonTable.add(backButton).padRight(20);

        TextButton startButton = new TextButton("START GAME", skin);
        startButton.addListener(new ClickListener() {
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                user.setSelectedTime(timeSelectBox.getSelected());
                controller.handleStartGame();
            }
        });
        buttonTable.add(startButton);

        mainTable.add(buttonTable).colspan(2).padBottom(50);

        stage.addActor(mainTable);
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
