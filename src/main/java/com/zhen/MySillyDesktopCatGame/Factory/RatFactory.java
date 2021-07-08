package com.zhen.MySillyDesktopCatGame.Factory;

import com.zhen.MySillyDesktopCatGame.Model.Rat;
import com.zhen.MySillyDesktopCatGame.Type.RatType;

public abstract class RatFactory {

    private RatType ratType;
    private int id = 0;

    public RatFactory(RatType ratType) {
        this.ratType = ratType;
    }

    public abstract Rat createRat();
    protected int generateId(){
        return id++;
    }
}
