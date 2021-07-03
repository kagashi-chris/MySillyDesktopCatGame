package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Type.CatStateType;

public class CatBehaviorStateMachine {

    private MainController mainController;
    private State currentState;

    private State eating = new Eating();
    private State idleLeft = new IdleLeft();
    private State idleRight = new IdleRight();
    private State dying = new Dying();
    private State dead = new Dead();

    public CatBehaviorStateMachine(MainController mainController) {
        this.mainController = mainController;
        this.currentState = new IdleLeft();

    }

    public void nextState(CatBehaviorStateMachineInputs inputs)
    {
        this.currentState = this.currentState.nextState(inputs);
        System.out.println("Changing state to " + this.currentState.getClass().getName());
    }

    public State getCurrentState()
    {
        return currentState;
    }

    interface State{

        State nextState(CatBehaviorStateMachineInputs inputs);
        CatStateType getCatStateType();
    }

    class IdleLeft implements State{

        @Override
        public State nextState(CatBehaviorStateMachineInputs inputs) {
            if(inputs.isFeedButtonPressed())
            {
                return eating;
            }
            else if(inputs.getCat().getFullness()<=0)
            {
                return dead;
            }
            else if(inputs.getCat().getFullness()<=30000 && inputs.getCat().getFullness() > 0)
            {
                return dying;
            }
            else if(inputs.isDirectionRandomlyChanged())
            {
                return idleRight;
            }
            else
            {
                return idleLeft;
            }
        }

        @Override
        public CatStateType getCatStateType() {
            return CatStateType.IDLE_LEFT;
        }
    }

    class IdleRight implements State{
        @Override
        public State nextState(CatBehaviorStateMachineInputs inputs) {
            if(inputs.isFeedButtonPressed())
            {
                return eating;
            }
            else if(inputs.getCat().getFullness()<=0)
            {
                return dead;
            }
            else if(inputs.getCat().getFullness()<=30000 && inputs.getCat().getFullness() > 0)
            {
                return dying;
            }
            else if(inputs.isDirectionRandomlyChanged())
            {
                return idleLeft;
            }
            else
            {
                return idleRight;
            }
        }

        @Override
        public CatStateType getCatStateType() {
            return CatStateType.IDLE_RIGHT;
        }
    }

    class Eating implements State{
        @Override
        public State nextState(CatBehaviorStateMachineInputs inputs) {

            if(inputs.getEatingTimer() <= 0)
            {
                return idleRight;
            }
            else if(inputs.getCat().getFullness() <= 30000)
            {
                return dying;
            }
            else
            {
                return eating;
            }

        }

        @Override
        public CatStateType getCatStateType() {
            return CatStateType.EATING;
        }
    }

    class Dying implements State{
        @Override
        public State nextState(CatBehaviorStateMachineInputs inputs) {

            if(inputs.feedButtonPressed)
            {
                return eating;
            }
            else if(inputs.getCat().getFullness() <= 0)
            {
                return dead;
            }
            else
            {
                return dying;
            }
        }

        @Override
        public CatStateType getCatStateType() {
            return CatStateType.DYING;
        }
    }

    class Dead implements State{
        @Override
        public State nextState(CatBehaviorStateMachineInputs inputs) {
            return dead;
        }

        @Override
        public CatStateType getCatStateType() {
            return CatStateType.DEAD;
        }
    }
}

 class CatBehaviorStateMachineInputs {

    boolean directionRandomlyChanged;
    boolean feedButtonPressed;
    int eatingTimer;
    Cat cat;

    public CatBehaviorStateMachineInputs(boolean feedButtonPressed, int eatingTimer, Cat cat, boolean directionRandomlyChanged) {
        this.feedButtonPressed = feedButtonPressed;
        this.eatingTimer = eatingTimer;
        this.cat = cat;
        this.directionRandomlyChanged = directionRandomlyChanged;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public boolean isFeedButtonPressed() {
        return feedButtonPressed;
    }

    public void setFeedButtonPressed(boolean feedButtonPressed) {
        this.feedButtonPressed = feedButtonPressed;
    }

    public int getEatingTimer() {
        return eatingTimer;
    }

    public void setEatingTimer(int eatingTimer) {
        this.eatingTimer = eatingTimer;
    }

     public boolean isDirectionRandomlyChanged() {
         return directionRandomlyChanged;
     }

     public void setDirectionRandomlyChanged(boolean directionRandomlyChanged) {
         this.directionRandomlyChanged = directionRandomlyChanged;
     }

     @Override
     public String toString() {
         return "CatBehaviorStateMachineInputs{" +
                 "directionRandomlyChanged=" + directionRandomlyChanged +
                 ", feedButtonPressed=" + feedButtonPressed +
                 ", eatingTimer=" + eatingTimer +
                 ", cat=" + cat +
                 '}';
     }
 }



