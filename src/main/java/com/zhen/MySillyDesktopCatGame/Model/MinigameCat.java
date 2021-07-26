package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.CatMiniGameStateType;

public class MinigameCat extends Animal{

    private int health = 10;

    public MinigameCat(int x, int y) {
        super(CatMiniGameStateType.IDLE, x, y);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "MinigameCat{" +
                "x=" + x +
                ", y=" + y +
                ", animalStateType=" + animalStateType +
                ", health=" + health +
                '}';
    }
}
