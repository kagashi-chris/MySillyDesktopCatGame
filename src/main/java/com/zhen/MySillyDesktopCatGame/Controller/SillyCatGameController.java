package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Action.FeedAction;
import com.zhen.MySillyDesktopCatGame.Action.MakeHungryDebugAction;
import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Type.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SillyCatGameController{

    private MainController mainController;
    private CatBehaviorStateMachine catBehaviorStateMachine;
    private CatBehaviorStateMachineInputs catBehaviorStateMachineInputs;
    private boolean feedButtonPressed;
    private int eatingTimer = 0;
    private int tickSkipCounter = 0;

    public SillyCatGameController(MainController mainController) {
        this.mainController = mainController;
        catBehaviorStateMachine = new CatBehaviorStateMachine(mainController);
        this.catBehaviorStateMachineInputs = new CatBehaviorStateMachineInputs(
                feedButtonPressed,
                eatingTimer,
                mainController.getGameState().getCatList().get(0),
                false
        );
    }

    public void handleFeedCat(FeedAction action)
    {
        Cat cat = action.getCat();
        cat.setFullness(cat.getFullness()+30000);
        cat.setCatStateType(CatStateType.EATING);
        System.out.println(cat.getFullness());
        feedButtonPressed = true;
        eatingTimer = 6;
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
    private void catDecayHunger(Cat cat)
    {
        cat.setFullness(cat.getFullness()-1);
        System.out.println("Current Hunger Value After Decay: " + cat.getFullness());
    }

    private void decayEatingTimer()
    {
        if(eatingTimer > 0)
        {
            eatingTimer--;
        }
    }

    private void resetButtons()
    {
        feedButtonPressed = false;
    }

    public void tick()
    {
        if(tickSkipCounter++ < 30)return;
        tickSkipCounter = 0;
        //this is the default stuff that happens every tick
        catDecayHunger(mainController.getGameState().getCatList().get(0));
        decayEatingTimer();
        //determine inputs
        catBehaviorStateMachineInputs.setFeedButtonPressed(feedButtonPressed);
        catBehaviorStateMachineInputs.setEatingTimer(eatingTimer);
        catBehaviorStateMachineInputs.setCat(mainController.getGameState().getCatList().get(0));
        catBehaviorStateMachineInputs.setDirectionRandomlyChanged(determineDirectionRandomlyChanged());
        System.out.println(catBehaviorStateMachineInputs.toString());

        //determine next state
        catBehaviorStateMachine.nextState(catBehaviorStateMachineInputs);
        CatBehaviorStateMachine.State catState = catBehaviorStateMachine.getCurrentState();

        //determine effects of new state
        mainController.getGameState().getCatList().get(0).setCatStateType(catState.getCatStateType());
        resetButtons();
        if(catState.getCatStateType() == CatStateType.EATING)
        {
            mainController.getGameState().setButtonsDisabled(true);
        }
        else
        {
            mainController.getGameState().setButtonsDisabled(false);
        }
    }

    public boolean determineDirectionRandomlyChanged()
    {
        return (int)(Math.random()*3)+1 == 1;
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
            cat.setCatStateType(CatStateType.IDLE_LEFT);
        }

    }



}
