package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

public class GameState {

    private boolean buttonsDisabled;
    private GameStateType gameStateType;
    private Cat cat;

    public GameState(){
    }

    public GameState(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
    }

    public GameStateType getGameStateType() {
        return gameStateType;
    }

    public void setGameStateType(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
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
                ", cat=" + cat +
                '}';
    }
}
