package com.zhen.MySillyDesktopCatGame.Action;

import com.zhen.MySillyDesktopCatGame.Model.Cat;

public class MakeHungryDebugAction implements Action{

    private Cat cat;

    public MakeHungryDebugAction(Cat cat) {
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
