package com.zhen.MySillyDesktopCatGame.Action;

import com.zhen.MySillyDesktopCatGame.Model.Rat;

public class DamageRatAction implements Action{

    private Rat rat;

    public DamageRatAction(Rat rat) {
        this.rat = rat;
    }

    public Rat getRat() {
        return rat;
    }

    public void setRat(Rat rat) {
        this.rat = rat;
    }
}
