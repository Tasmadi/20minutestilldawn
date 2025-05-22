package com.tilldawn.Models.Enums;

public enum Weapon {
    REVOLVER(6, 1, 1, 20),
    SHOTGUN(2, 1, 4, 10),
    SMGS_DUAL(24, 2, 1, 8);

    private final int ammo;
    private final int reloadTimeSeconds;
    private final int projectileCount;
    private final int damage;

    Weapon(int ammo, int reloadTimeSeconds, int projectileCount, int damage) {
        this.ammo = ammo;
        this.reloadTimeSeconds = reloadTimeSeconds;
        this.projectileCount = projectileCount;
        this.damage = damage;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getReloadTimeSeconds() {
        return reloadTimeSeconds;
    }

    public int getProjectileCount() {
        return projectileCount;
    }

    public int getDamage() {
        return damage;
    }

    public int getScore() {
        return 10;
    }
}
