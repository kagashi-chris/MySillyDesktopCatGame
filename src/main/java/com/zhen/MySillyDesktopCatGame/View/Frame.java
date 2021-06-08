package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.ViewController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private GameState gameState;
    private Menu menu;
    private Game game;
    private JPanel panelController = new JPanel();
    private CardLayout cl = new CardLayout();
    private ViewController viewController;

    public Frame(GameWindow gameWindow, GameState gameState, ViewController viewController){
        this.viewController = viewController;
        this.gameState = gameState;

        panelController.setLayout(cl);
        menu = new Menu(viewController);
        panelController.add(menu, "menu");
        cl.show(panelController, "menu");


        this.setSize(gameWindow.getGameWindowWidth(), gameWindow.getGameWindowHeight());
        this.add(panelController);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void repaint(){
        if(gameState.getGameStateType().equals(GameStateType.MENU))
        {
            cl.show(panelController,"menu");
        }
        else if(gameState.getGameStateType().equals(GameStateType.GAME))
        {
            if(game == null)
            {
                game = new Game(viewController);
                panelController.add(game, "game");
            }
            cl.show(panelController,"game");
        }
    }
}
