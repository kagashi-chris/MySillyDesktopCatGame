package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

public class GameState {

    private GameStateType gameStateType;

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

    @Override
    public String toString() {
        return "GameState{" +
                "gameStateType=" + gameStateType +
                '}';
    }
}
