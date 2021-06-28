package com.zhen.MySillyDesktopCatGame.Action;

import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

public class SwitchScreenToAction implements Action{

    private GameStateType gameStateType;

    public SwitchScreenToAction(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
    }

    public GameStateType getGameStateType() {
        return gameStateType;
    }

    public void setGameStateType(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
    }
}
