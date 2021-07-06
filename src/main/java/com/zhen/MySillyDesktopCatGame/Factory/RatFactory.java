package com.zhen.MySillyDesktopCatGame.Factory;

import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Model.Rat;

public class RatFactory {

    public Rat createRat()
    {
        int speed = (int)(Math.random()*3)+1;
        int hp = (int)(Math.random()*5)+1;
        int x = GameWindow.GAME_WINDOW_WIDTH;
        int y = GameWindow.GAME_WINDOW_HEIGHT - 100;
        return new Rat(hp,speed,x,y);
    }
}
