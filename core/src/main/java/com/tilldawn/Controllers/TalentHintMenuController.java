package com.tilldawn.Controllers;

import com.tilldawn.Main;
import com.tilldawn.Models.*;
import com.tilldawn.Views.MainMenuView;
import com.tilldawn.Views.TalentHintMenuView;

import java.util.ArrayList;
import java.util.List;

public class TalentHintMenuController {
    private TalentHintMenuView view;
    private final User user = App.currentUser;

    public TalentHintMenuController() {

    }

    public void setView(TalentHintMenuView view) {
        this.view = view;
    }

    public List<String> getHeroHints() {
        List<String> hints = new ArrayList<>();
        hints.add("Knight: Strong melee attack, high defense.");
        hints.add("Archer: Long-range attacks, fast movement.");
        hints.add("Mage: High damage magic, low defense.");
        return hints;
    }

    public List<String> getCurrentKeyBindings() {
        String scheme = user.getControlScheme();
        if ("ARROWS".equalsIgnoreCase(scheme)) {
            return List.of("Move: Arrow Keys", "Interact: Enter", "Attack: Right Ctrl");
        }
        else {
            return List.of("Move: W/A/S/D", "Interact: E", "Attack: Space");
        }
    }

    public List<String> getCheatDescriptions() {
        return CheatCode.getAllCheatDescriptions();
    }

    public List<String> getAbilityDescriptions() {
        return AbilityInfo.getAllAbilityDescriptions();
    }

    public void handleBackButton() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(),
            GameAssetManager.getGameAssetManager().getSkin()));
    }
}
