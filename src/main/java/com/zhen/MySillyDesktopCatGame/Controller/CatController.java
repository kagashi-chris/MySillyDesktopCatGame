package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Type.CatStateType;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class CatController {

    private Cat cat;

    public CatController(Cat cat) {
        this.cat = cat;
        initCatState();
    }

    public void initCatState()
    {
        int minutesSinceLastUpdate = compareLastSavedDateInMinutes();
        int currentHunger = cat.getFullness() - (minutesSinceLastUpdate/6);
        int currentHappiness = cat.getHappiness() - (minutesSinceLastUpdate/6);

        if(currentHappiness < 0 || currentHunger < 0)
        {
            setCatState(CatStateType.DEAD);
        }
        else if(currentHappiness < 30 || currentHunger < 30)
        {
            setCatState(CatStateType.DYING);
        }
        else
        {
            setCatState(CatStateType.IDLE);
        }

    }

    public void setCatState(CatStateType catStateType)
    {
        cat.setCatStateType(catStateType);
    }

    //gets current time and compare it to the saved time inside cat.csv and return the difference in minutes
    public int compareLastSavedDateInMinutes()
    {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime savedTime = cat.getCatLastUpdated();
        int minutes = (int)savedTime.until(currentTime, ChronoUnit.MINUTES);
        return minutes;
    }

    public void feedCat()
    {
        cat.setFullness(cat.getFullness()+30);
    }

    public Cat getCat()
    {
        return this.cat;
    }

}
