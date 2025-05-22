package com.tilldawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controllers.TalentHintMenuController;
import com.tilldawn.Models.GameAssetManager;

public class TalentHintMenuView implements Screen {
    private final TalentHintMenuController controller;
    private final Stage stage;
    private final Skin skin;
    private Image background;

    public TalentHintMenuView(TalentHintMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        this.controller.setView(this);
        setupUI();
    }

    private void setupUI() {
        background = GameAssetManager.getGameAssetManager().getTalentHintMenuBackGroundImage();
        background.setFillParent(true);
        stage.addActor(background);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(50);

        Label title = new Label("", skin);
        title.setFontScale(2f);
        title.setColor(Color.GOLDENROD);
        mainTable.add(title).padBottom(30).row();

        mainTable.add(createSectionLabel("Hero Hints:")).left().row();
        for (String heroHint : controller.getHeroHints()) {
            mainTable.add(new Label("• " + heroHint, skin)).left().padLeft(20).row();
        }

        mainTable.add(createSectionLabel("Current Keybindings:")).padTop(30).left().row();
        for (String key : controller.getCurrentKeyBindings()) {
            mainTable.add(new Label("• " + key, skin)).left().padLeft(20).row();
        }

        mainTable.add(createSectionLabel("Cheat Codes:")).padTop(30).left().row();
        for (String cheat : controller.getCheatDescriptions()) {
            mainTable.add(new Label("• " + cheat, skin)).left().padLeft(20).row();
        }

        mainTable.add(createSectionLabel("Abilities:")).padTop(30).left().row();
        for (String ability : controller.getAbilityDescriptions()) {
            mainTable.add(new Label("• " + ability, skin)).left().padLeft(20).row();
        }

        TextButton backButton = new TextButton("BACK", skin);
        mainTable.add(backButton).padTop(40);
        stage.addActor(mainTable);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.handleBackButton();
            }
        });
    }

    private Label createSectionLabel(String title) {
        Label label = new Label(title, skin);
        label.setFontScale(1.3f);
        label.setColor(Color.CYAN);
        return label;
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void show() { Gdx.input.setInputProcessor(stage); }
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() { stage.dispose(); }
}
