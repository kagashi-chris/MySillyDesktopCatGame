package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Factory.RatFactory;
import com.zhen.MySillyDesktopCatGame.Model.Rat;

import java.util.HashMap;
import java.util.Map;

public class MinigameController {

    private MainController mainController;
    private RatFactory ratFactory;
    private int ratId = 0;
    private Map<Integer, Rat> ratMap = new HashMap<>();

    public MinigameController(MainController mainController) {
        this.mainController = mainController;
        ratFactory = new RatFactory();
    }

    public void randomlyCreateRat()
    {
        if((int)(Math.random()*3)+1 == 1)
        {
            ratMap.put(ratId,ratFactory.createRat());
            ratId++;
        }
    }

    private void animateRat()
    {
        for(Map.Entry<Integer, Rat> ratEntry: ratMap.entrySet())
        {
            ratEntry.getValue().setX(ratEntry.getValue().getX() - ratEntry.getValue().getSpeed());
        }
    }

    public void tick()
    {
        randomlyCreateRat();
        animateRat();
    }
}
