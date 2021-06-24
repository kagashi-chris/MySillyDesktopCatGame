package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Type.*;
import com.zhen.MySillyDesktopCatGame.View.SillyCatGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class SillyCatGameController implements ActionListener {

    private List<SillyCatGameView> sillyCatGameViewObservers;
    private MainController mainController;
    private Cat cat;

    public SillyCatGameController(MainController mainController) {
        this.mainController = mainController;
        initCat();
    }

    public void initCat()
    {
        cat = Cat.getInstance();
        //TODO read from file and set cat params
        mainController.getGameState().setCat(cat);
    }

//    public void initCatState(Cat cat)
//    {
//        int minutesSinceLastUpdate = compareLastSavedDateInMinutes();
//        int currentHunger = cat.getFullness() - (minutesSinceLastUpdate/6);
//        int currentHappiness = cat.getHappiness() - (minutesSinceLastUpdate/6);
//
//        if(currentHappiness < 0 || currentHunger < 0)
//        {
//            setCatState(CatStateType.DEAD);
//        }
//        else if(currentHappiness < 30000 || currentHunger < 30000)
//        {
//            setCatState(CatStateType.DYING);
//        }
//        else
//        {
//            setCatState(CatStateType.IDLE);
//        }
//
//    }

    public void setCatState(CatStateType catStateType)
    {
        mainController.getGameState().getCat().setCatStateType(catStateType);
    }

    //gets current time and compare it to the saved time inside cat.csv and return the difference in minutes
    public int compareLastSavedDateInMinutes()
    {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime savedTime = cat.getCatLastUpdated();
        int minutes = (int)savedTime.until(currentTime, ChronoUnit.MINUTES);
        return minutes;
    }

    //cat loses 1point of hunger every second
    public void catDecayHunger()
    {
        cat.setFullness(cat.getFullness()-1);
        System.out.println("Current Hunger Value After Decay: " + cat.getFullness());
    }

    public void updateCatState()
    {
        if(cat.getFullness()>30000 && !cat.getCatStateType().equals(CatStateType.EATING))
        {
            setCatState(CatStateType.IDLE);
        }
        else if(cat.getFullness()<=30000 && cat.getFullness() > 0 && !cat.getCatStateType().equals(CatStateType.EATING))
        {
            setCatState(CatStateType.DYING);
        }
        else if(cat.getFullness()<=0)
        {
            setCatState(CatStateType.DEAD);
        }
    }

    //TODO debug get rid after
    public void hungryCat()
    {
        cat.setFullness(cat.getFullness()-30000);
    }

    public Cat getCat()
    {
        return this.cat;
    }

    public void performAction(Action action)
    {
        if (action instanceof FeedAction)
        {
            handleFeedCat((FeedAction) action);
        }

    }

    private void notifyObservers(GameState gameState)
    {
        for(SillyCatGameView sillyCatGameView: sillyCatGameViewObservers)
        {
            sillyCatGameView.updateView(gameState);
        }
    }

    public void handleFeedCat(FeedAction feedAction)
    {
        GameState gameState = mainController.getGameState();
        Cat cat = gameState.getCat();
        cat.setFullness(cat.getFullness()+30000);
        gameState.setButtonsDisabled(true);
        notifyObservers(gameState);
    }

    public void subscribe(SillyCatGameView sillyCatGameView)
    {
        this.sillyCatGameViewObservers.add(sillyCatGameView);
    }

    public void unsubscribe(SillyCatGameView sillyCatGameView)
    {
        this.sillyCatGameViewObservers.remove(sillyCatGameView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
