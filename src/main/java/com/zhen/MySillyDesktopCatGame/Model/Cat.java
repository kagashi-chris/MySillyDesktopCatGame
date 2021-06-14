package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.CatStateType;

import java.time.LocalDateTime;

public class Cat {

    private int fullness;
    private int happiness;
    private CatStateType catStateType;
    private LocalDateTime catLastUpdated;

    public Cat() {
        fullness = 50;
        happiness = 50;
        catStateType = CatStateType.IDLE;
        catLastUpdated = LocalDateTime.now();
    }

    public Cat(int fullness, int happiness) {
        this.fullness = fullness;
        this.happiness = happiness;
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

    public CatStateType getCatStateType() {
        return catStateType;
    }

    public void setCatStateType(CatStateType catStateType) {
        this.catStateType = catStateType;
    }

    public LocalDateTime getCatLastUpdated() {
        return catLastUpdated;
    }

    public void setCatLastUpdated(LocalDateTime catLastUpdated) {
        this.catLastUpdated = catLastUpdated;
    }
}
