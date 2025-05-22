package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.tilldawn.Models.Enums.Hero;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("Skin/pixthulhu-ui.json"));
    private final Texture welcomeMenuBackGroundTexture = new Texture("welcomeMenuBackGround.png");
    private final Image welcomeMenuBackGroundImage = new Image(new TextureRegion(welcomeMenuBackGroundTexture));
    private final Texture signUpMenuBackGroundTexture = new Texture("signUpMenuBackGround.png");
    private final Image signUpMenuBackGroundImage = new Image(new TextureRegion(signUpMenuBackGroundTexture));
    private final Texture loginMenuBackGroundTexture = new Texture("loginMenuBackGround.png");
    private final Image loginMenuBackGroundImage = new Image(new TextureRegion(loginMenuBackGroundTexture));
    private final Texture forgotPasswordMenuBackGroundTexture = new Texture("forgotPasswordMenuBackGround.png");
    private final Image forgotPasswordMenuBackGroundImage = new Image(new TextureRegion(forgotPasswordMenuBackGroundTexture));
    private final Texture mainMenuBackGroundTexture = new Texture("mainMenuBackGround.png");
    private final Image mainMenuBackGroundImage = new Image(new TextureRegion(mainMenuBackGroundTexture));
    private final Texture settingMenuBackGround = new Texture("settingMenuBackGround.png");
    private final Image settingMenuBackGroundImage = new Image(new TextureRegion(settingMenuBackGround));
    private final Texture profileMenuBackGround = new Texture("profileMenuBackGround.png");
    private final Image profileMenuBackGroundImage = new Image(new TextureRegion(profileMenuBackGround));
    private final Texture chooseAvatarMenuBackGround = new Texture("chooseAvatarMenuBackGround.png");
    private final Image chooseAvatarMenuBackGroundImage = new Image(new TextureRegion(chooseAvatarMenuBackGround));
    private final Texture chooseHeroMenuBackGround = new Texture("chooseHeroMenuBackGround.png");
    private final Image chooseHeroMenuBackGroundImage = new Image(new TextureRegion(chooseHeroMenuBackGround));
    private final Texture preGameMenuBackGround = new Texture("preGameMenuBackGround.png");
    private final Image preGameMenuBackGroundImage = new Image(new TextureRegion(preGameMenuBackGround));
    private final Texture talentHintMenuBackGround = new Texture("talentHintMenuBackGround.png");
    private final Image talentHintMenuBackGroundImage = new Image(new TextureRegion(talentHintMenuBackGround));

    private GameAssetManager(){

    }

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Texture getWelcomeMenuBackGround() {
        return welcomeMenuBackGroundTexture;
    }

    public Image getWelcomeMenuBackGroundImage() {
        return welcomeMenuBackGroundImage;
    }

    public Texture getSignUpMenuBackGround() {
        return signUpMenuBackGroundTexture;
    }

    public Image getSignUpMenuBackGroundImage() {
        return signUpMenuBackGroundImage;
    }

    public Texture getLoginMenuBackGround() {
        return loginMenuBackGroundTexture;
    }

    public Image getLoginMenuBackGroundImage() {
        return loginMenuBackGroundImage;
    }

    public Texture getForgotPasswordMenuBackGround() {
        return forgotPasswordMenuBackGroundTexture;
    }

    public Image getForgotPasswordMenuBackGroundImage() {
        return forgotPasswordMenuBackGroundImage;
    }

    public Texture getMainMenuBackGround() {
        return mainMenuBackGroundTexture;
    }

    public Image getMainMenuBackGroundImage() {
        return mainMenuBackGroundImage;
    }

    public Texture getSettingMenuBackGround() {
        return settingMenuBackGround;
    }

    public Image getSettingMenuBackGroundImage() {
        return settingMenuBackGroundImage;
    }

    public Texture getProfileMenuBackGround() {
        return profileMenuBackGround;
    }

    public Image getProfileMenuBackGroundImage() {
        return profileMenuBackGroundImage;
    }

    public Texture getChooseAvatarMenuBackGround() {
        return chooseAvatarMenuBackGround;
    }

    public Image getChooseAvatarMenuBackGroundImage() {
        return chooseAvatarMenuBackGroundImage;
    }

    public Texture getChooseHeroMenuBackGround() {
        return chooseHeroMenuBackGround;
    }

    public Image getChooseHeroMenuBackGroundImage() {
        return chooseHeroMenuBackGroundImage;
    }

    public Texture getPreGameMenuBackGround() {
        return preGameMenuBackGround;
    }

    public Image getPreGameMenuBackGroundImage() {
        return preGameMenuBackGroundImage;
    }

    public Texture getTalentHintMenuBackGround() {
        return talentHintMenuBackGround;
    }

    public Image getTalentHintMenuBackGroundImage() {
        return talentHintMenuBackGroundImage;
    }

    public static Image getAvatarImage(int avatarId) {
        String imagePath = "avatars/avatar_" + avatarId + ".png";
        Texture avatarTexture = new Texture(Gdx.files.internal(imagePath));
        Image avatarImage = new Image(new TextureRegion(avatarTexture));
        return avatarImage;
    }

    public static Image getHeroImage(Hero hero) {
        Texture texture = new Texture(Gdx.files.internal("heros/" + hero.name().toLowerCase() + ".png"));
        return new Image(texture);
    }
}
