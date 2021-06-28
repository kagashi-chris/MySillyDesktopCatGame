package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.swing.*;
import java.awt.*;

//TODO remove timer from this class and update the tick in mainController
public class MainWindowView extends JFrame implements View{

    private MenuView menuView;
    private SillyCatGameView sillyCatGameView;
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

        mainController.subscribe(this);
        this.setVisible(true);
    }

    public void mainViewLoop(){
        repaint();
    }

    public void switchScreenTo(GameStateType gameStateType)
    {
        switch(gameStateType)
        {
            case SILLY_CAT_GAME:
                sillyCatGameView = SillyCatGameView.getInstance(mainController);
                if(sillyCatGameView.getParent() != panelController)
                {
                    panelController.add(sillyCatGameView, "game");
                }
                layout.show(panelController,"game");
                break;

            case MENU:
                menuView = MenuView.getInstance(mainController);
                if(menuView.getParent() != panelController)
                {
                    panelController.add(menuView, "menu");
                }
                layout.show(panelController,"menu");
                break;

            default:
                break;
        }
    }

    @Override
    public void updateView(GameState gameState) {
        switchScreenTo(gameState.getGameStateType());
    }
}
