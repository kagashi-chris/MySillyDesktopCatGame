package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.View.Frame;
import com.zhen.MySillyDesktopCatGame.View.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController{

    //View controller manages what users see. it creates the window and graphics that are displayed on screen
    //View Controller is an observer to Menu/Game/Shop. When certain conditions are met (example:Pressing play button)
    //then the view will notify viewController and it will manipulate game state to GameStateType.PLAY

    private GameState gameState;
    private GameWindow gameWindow;

    public ViewController(GameState gameState, GameWindow gameWindow) {
        this.gameState = gameState;
        this.gameWindow = gameWindow;
    }

    public void setGameState(GameStateType gameStateType)
    {
        gameState.setGameStateType(gameStateType);
        System.out.println("Game state set to: " + gameStateType.toString());
        if(gameStateType.equals(GameStateType.EXIT))
        {
            System.exit(1);
        }
    }

}
