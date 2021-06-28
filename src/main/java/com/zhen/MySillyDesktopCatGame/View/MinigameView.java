package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;

public class MinigameView implements View{

    private MainController mainController;

    public MinigameView(MainController mainController) {
        this.mainController = mainController;

        mainController.subscribe(this);
    }

    @Override
    public void updateView(GameState gameState) {
    }
}
