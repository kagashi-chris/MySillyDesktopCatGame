package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.RatType;

public class TankyRat extends Rat{

    public TankyRat(int hp, int speed, int x, int y, int id) {
        super(RatType.TANKY, hp, speed, x, y, id);
    }
}
