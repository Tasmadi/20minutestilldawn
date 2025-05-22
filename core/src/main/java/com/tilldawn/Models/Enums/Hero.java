package com.tilldawn.Models.Enums;

public enum Hero {
    SHANA(4, 4),
    DIAMOND(7, 1),
    SCARLETT(3, 5),
    LILITH(5, 3),
    DASHER(2, 10);

    private final int speed;
    private final int hp;

    Hero(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public int getScore() {
        return speed + hp;
    }
}
