package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.RatType;

public class NormalRat extends Rat{

    public NormalRat(int hp, int speed, int x, int y, int id) {
        super(RatType.NORMAL, hp, speed, x, y, id);
    }
}
