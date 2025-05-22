package com.tilldawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controllers.ChooseHeroMenuController;
import com.tilldawn.Controllers.SignUpMenuController;
import com.tilldawn.Models.App;
import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.GameAssetManager;
import com.tilldawn.Models.User;

public class ChooseHeroMenuView implements Screen {
    private final ChooseHeroMenuController controller;
    private final Stage stage;
    private final Skin skin;
    private final User user = App.currentUser;
    private Image background;
    private Image selectedHeroImage;

    public ChooseHeroMenuView(ChooseHeroMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.controller.setView(this);

        setupUI();
    }

    public void setupUI() {
        background = GameAssetManager.getGameAssetManager().getChooseHeroMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(50);

//        mainTable.add(new Label("Choose Your Hero", skin)).colspan(5).padBottom(30).row();

        mainTable.add().colspan(2).padBottom(350).row();

        Table heroesTable = new Table();
        for (Hero hero : Hero.values()) {
            Table heroCard = new Table();

            Image heroImg = GameAssetManager.getHeroImage(hero);
            heroImg.setSize(150, 150);

            if (hero == user.getSelectedCharacter()) {
                heroImg.setColor(Color.GOLD);
                selectedHeroImage = heroImg;
            }

            heroImg.addListener(new ClickListener() {
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    if (selectedHeroImage != null) {
                        selectedHeroImage.setColor(Color.WHITE);
                    }
                    heroImg.setColor(Color.GOLD);
                    selectedHeroImage = heroImg;
                    user.setSelectedCharacter(hero);
                }
            });

            heroCard.add(heroImg).size(150).pad(5).row();

            heroCard.add(new Label(hero.name(), skin)).row();

            heroCard.add(new Label("HP: " + hero.getHp(), skin)).row();
            heroCard.add(new Label("Speed: " + hero.getSpeed(), skin)).row();
            heroCard.add(new Label("Score: " + hero.getScore(), skin)).padBottom(10).row();

            heroesTable.add(heroCard).pad(10);
        }

        mainTable.add(heroesTable).colspan(5).padBottom(30).row();
        mainTable.add().colspan(2).padBottom(100).row();

        TextButton confirmButton = new TextButton("Confirm", skin);
        confirmButton.addListener(new ClickListener() {
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                controller.handleConfirmButton();
            }
        });
        mainTable.add(confirmButton).colspan(5).center();

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
