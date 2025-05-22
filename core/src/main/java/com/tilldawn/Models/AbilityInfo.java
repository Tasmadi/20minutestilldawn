package com.tilldawn.Models;

import java.util.List;

public class AbilityInfo {
    public static List<String> getAllAbilityDescriptions() {
        return List.of(
            "Fireball - Deals AoE magic damage",
            "Stealth - Become invisible to enemies for 5 seconds",
            "Shield Bash - Stuns enemies in front"
        );
    }
}
