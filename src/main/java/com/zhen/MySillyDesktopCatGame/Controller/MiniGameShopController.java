package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Action.BuyItemAction;

public class MiniGameShopController {

    private MainController mainController;

    public MiniGameShopController(MainController mainController) {
        this.mainController = mainController;
    }

    public void buyItem(BuyItemAction action)
    {
        System.out.println(action.getSpellType());
    }
}
