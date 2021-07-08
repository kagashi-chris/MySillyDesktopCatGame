package com.zhen.MySillyDesktopCatGame.Factory;

import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Model.Rat;
import com.zhen.MySillyDesktopCatGame.Model.TankyRat;
import com.zhen.MySillyDesktopCatGame.Type.RatType;

public class TankyRatFactory extends RatFactory{

    public TankyRatFactory() {
        super(RatType.TANKY);
    }

    @Override
    public Rat createRat() {
        int speed = (int)(Math.random()*3)+1;
        int hp = (int)(Math.random()*5)+1;
        int x = GameWindow.GAME_WINDOW_WIDTH-150;
        int y = GameWindow.GAME_WINDOW_HEIGHT - 100;
        return new TankyRat(hp,speed,x,y,generateId());
    }

}
