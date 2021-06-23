package com.zhen.MySillyDesktopCatGame.Controller;

public class AnimationController {

    private MainController mainController;
    private int hungerValue;

    public AnimationController(MainController mainController) {
        this.mainController = mainController;
        hungerValue = mainController.getGameState().getCat().getFullness();
        State.idle = new Idle();
        State.eating = new Eating();
        State.dying = new Dying();
        State.dead = new Dead();

        State.current =
    }
}

abstract class State{

    static State idle, eating, dying, dead, current;

    void enter(){}
    void update(){}
}

class Idle extends State{

    void enter()
    {

    }

    void update()
    {
        switch()
    }
}

class Eating extends State{

    void enter()
    {

    }

    void update()
    {

    }
}

class Dying extends State{

    void enter()
    {

    }

    void update()
    {

    }
}

class Dead extends State{

    void enter()
    {

    }

    void update()
    {

    }
}


