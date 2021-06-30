package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private boolean buttonsDisabled;
    private GameStateType gameStateType;
    private List<Cat> catList;

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

    @Override
    public String toString() {
        return "GameState{" +
                "buttonsDisabled=" + buttonsDisabled +
                ", gameStateType=" + gameStateType +
                ", cat=" + catList +
                '}';
    }
}
