package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Model.Cat;

public class AnimationController {

    private MainController mainController;
    private State currentState;

    private State eating = new Eating();
    private State idleLeft = new IdleLeft();
    private State idleRight = new IdleRight();
    private State dying = new Dying();
    private State dead = new Dead();

    public AnimationController(MainController mainController) {
        this.mainController = mainController;
        this.currentState = new IdleLeft();

    }

    public void nextState(AnimationControllerInputs inputs)
    {
        this.currentState = this.currentState.nextState(inputs);
        System.out.println("Changing state to " + this.currentState.getClass().getName());
    }


    interface State{

        State nextState(AnimationControllerInputs inputs);
    }

    class IdleLeft implements State{

        @Override
        public State nextState(AnimationControllerInputs inputs) {
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
    }

    class IdleRight implements State{
        @Override
        public State nextState(AnimationControllerInputs inputs) {
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
    }

    class Eating implements State{
        @Override
        public State nextState(AnimationControllerInputs inputs) {

            if(inputs.getEatingTimer() <= 0 && inputs.getCat().isFacingRight())
            {
                return idleRight;
            }
            else if(inputs.getEatingTimer() <= 0 && !inputs.getCat().isFacingRight())
            {
                return idleLeft;
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
    }

    class Dying implements State{
        @Override
        public State nextState(AnimationControllerInputs inputs) {

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
    }

    class Dead implements State{
        @Override
        public State nextState(AnimationControllerInputs inputs) {
            return dead;
        }
    }


}


 class AnimationControllerInputs{

    boolean feedButtonPressed;
    int eatingTimer;
    Cat cat;

    public AnimationControllerInputs(boolean feedButtonPressed, int eatingTimer, Cat cat) {
        this.feedButtonPressed = feedButtonPressed;
        this.eatingTimer = eatingTimer;
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public boolean isDirectionRandomlyChanged()
    {
        return (Math.random()*10)+1 == 1;
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
}



