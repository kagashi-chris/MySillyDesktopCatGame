package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Controller.SillyCatGameController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.swing.*;
import java.awt.*;

//TODO remove timer from this class and update the tick in mainController
public class MainWindowView extends JFrame {

    private GameState gameState;
    private JPanel currentView;
    private MenuView menuView;
    private JPanel panelController = new JPanel();
    private CardLayout layout = new CardLayout();
    private MainController mainController;

    public MainWindowView(MainController mainController){
        this.mainController = mainController;

        this.setSize(GameWindow.GAME_WINDOW_WIDTH, GameWindow.GAME_WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelController.setLayout(layout);
        this.add(panelController);
        switchScreenTo(GameStateType.MENU);

        this.setVisible(true);
    }

    public void mainViewLoop(){
        repaint();
    }

    public void switchScreenTo(GameStateType gameStateType)
    {
        panelController.removeAll();
        switch(gameStateType)
        {
            case SILLY_CAT_GAME:
                currentView = SillyCatGameView.getInstance(mainController);
                panelController.add(currentView, "game");
                layout.show(panelController,"game");
                break;

            case MENU:
                currentView = MenuView.getInstance(mainController);
                panelController.add(currentView, "menu");
                layout.show(panelController,"menu");
                break;

            default:
                break;
        }
    }

}
