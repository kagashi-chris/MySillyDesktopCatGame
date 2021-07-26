package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.CatStateType;

import java.time.LocalDateTime;

public class Cat extends Animal{

    private int fullness;
    private int happiness;
    private LocalDateTime catLastUpdated;

    public Cat(int x, int y) {
        super(CatStateType.IDLE_LEFT, x, y);
        fullness = 50000;
        happiness = 50000;
        catLastUpdated = LocalDateTime.now();
    }

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public LocalDateTime getCatLastUpdated() {
        return catLastUpdated;
    }

    public void setCatLastUpdated(LocalDateTime catLastUpdated) {
        this.catLastUpdated = catLastUpdated;
    }


    @Override
    public String toString() {
        return "Cat{" +
                "x=" + x +
                ", y=" + y +
                ", animalStateType=" + animalStateType +
                ", fullness=" + fullness +
                ", happiness=" + happiness +
                ", catLastUpdated=" + catLastUpdated +
                '}';
    }
}
