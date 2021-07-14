package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameState {

    private boolean buttonsDisabled;
    private GameStateType gameStateType;
    private List<Cat> catList;
    private Set<Rat> ratSet = new HashSet<>();
    private int currentPoints = 0;

    public GameState(){
    }

    public GameState(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
        this.catList = new ArrayList<>();
        Cat cat = new Cat();
        catList.add(cat);
    }

    public GameStateType getGameStateType() {
        return gameStateType;
    }

    public void setGameStateType(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
    }

    public List<Cat> getCatList() {
        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }

    public boolean isButtonsDisabled() {
        return buttonsDisabled;
    }

    public void setButtonsDisabled(boolean buttonsDisabled) {
        this.buttonsDisabled = buttonsDisabled;
    }

    public Set<Rat> getRatSet() {
        return ratSet;
    }

    public void setRatSet(Set<Rat> ratSet) {
        this.ratSet = ratSet;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "buttonsDisabled=" + buttonsDisabled +
                ", gameStateType=" + gameStateType +
                ", catList=" + catList +
                ", ratSet=" + ratSet +
                ", currentPoints=" + currentPoints +
                '}';
    }
}
