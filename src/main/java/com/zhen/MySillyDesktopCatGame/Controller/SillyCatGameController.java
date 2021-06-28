package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Action.Action;
import com.zhen.MySillyDesktopCatGame.Action.FeedAction;
import com.zhen.MySillyDesktopCatGame.Action.MakeHungryDebugAction;
import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Type.*;
import com.zhen.MySillyDesktopCatGame.Util.SpriteUtil;
import com.zhen.MySillyDesktopCatGame.View.AnimatedSprite;
import com.zhen.MySillyDesktopCatGame.View.SillyCatGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class SillyCatGameController{

    private MainController mainController;

    public SillyCatGameController(MainController mainController) {
        this.mainController = mainController;
    }

    public void handleFeedCat(FeedAction action)
    {
        Cat cat = action.getCat();
        cat.setFullness(cat.getFullness()+30000);
        cat.setCatStateType(CatStateType.EATING);
        System.out.println(cat.getFullness());
        mainController.getGameState().setButtonsDisabled(true);
    }

    //TODO debug get rid after
    public void handleMakeHungryDebugAction(MakeHungryDebugAction action)
    {
        Cat cat = action.getCat();
        cat.setFullness(cat.getFullness()-30000);
        System.out.println(cat.getFullness());
    }

    //gets current time and compare it to the saved time inside cat.csv and return the difference in minutes
    public int compareLastSavedDateInMinutes(Cat cat)
    {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime savedTime = cat.getCatLastUpdated();
        int minutes = (int)savedTime.until(currentTime, ChronoUnit.MINUTES);
        return minutes;
    }

    //cat loses 1point of hunger every second
    public void catDecayHunger(Cat cat)
    {
        cat.setFullness(cat.getFullness()-1);
        System.out.println("Current Hunger Value After Decay: " + cat.getFullness());
    }

    public void updateCatState(Cat cat)
    {
        if(cat.getFullness()>30000 && !cat.getCatStateType().equals(CatStateType.EATING))
        {
            cat.setCatStateType(CatStateType.IDLE);
        }
        else if(cat.getFullness()<=30000 && cat.getFullness() > 0 && !cat.getCatStateType().equals(CatStateType.EATING))
        {
            cat.setCatStateType(CatStateType.DYING);
        }
        else if(cat.getFullness()<=0)
        {
            cat.setCatStateType(CatStateType.DEAD);
        }
        System.out.println(cat.getCatStateType());
    }

    public void initCatState(Cat cat)
    {
        int minutesSinceLastUpdate = compareLastSavedDateInMinutes(cat);
        int currentHunger = cat.getFullness() - (minutesSinceLastUpdate/6);
        int currentHappiness = cat.getHappiness() - (minutesSinceLastUpdate/6);

        if(currentHappiness < 0 || currentHunger < 0)
        {
            cat.setCatStateType(CatStateType.DEAD);
        }
        else if(currentHappiness < 30000 || currentHunger < 30000)
        {
            cat.setCatStateType(CatStateType.DYING);
        }
        else
        {
            cat.setCatStateType(CatStateType.IDLE);
        }

    }



}
