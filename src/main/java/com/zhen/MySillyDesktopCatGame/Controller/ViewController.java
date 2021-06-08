package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.View.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController implements ActionListener {

    //View controller manages what users see. it creates the window and graphics that are displayed on screen
    //View Controller is an observer to Menu/Game/Shop. When certain conditions are met (example:Pressing play button)
    //then the view will notify viewController and it will manipulate game state to GameStateType.PLAY

    private Window window;
    private GameState gameState;
    private GameWindow gameWindow;

    public ViewController(GameState gameState, GameWindow gameWindow, Window window) {
        this.gameState = gameState;
        this.gameWindow = gameWindow;
        this.window = window;
    }
    public void createFrame(){
        window.createFrame();
    }

    public void run()
    {
        window.render(window, gameState.getGameStateType());
    }

    public void update()
    {

        window.update(window, gameState.getGameStateType());
    }

    public void changeGameState(GameStateType gameStateType)
    {
        gameState.setGameStateType(gameStateType);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
