package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.AnimalStateType;

public abstract class Animal {

    int x;
    int y;
    AnimalStateType animalStateType;

    public Animal(AnimalStateType animalStateType, int x, int y) {
        this.x = x;
        this.y = y;
        this.animalStateType = animalStateType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public AnimalStateType getAnimalStateType() {
        return animalStateType;
    }

    public void setAnimalStateType(AnimalStateType animalStateType) {
        this.animalStateType = animalStateType;
    }
}
