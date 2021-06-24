package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {

    private MainController mainController;

    public MenuController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand())
        {
            case "Play":
                mainController.setGameState(GameStateType.SILLY_CAT_GAME);

                break;
            case "Exit":
                System.exit(1);
                break;
            default:
                break;
        }
    }
}
