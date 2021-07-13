package com.zhen.MySillyDesktopCatGame.Factory;

import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Model.NormalRat;
import com.zhen.MySillyDesktopCatGame.Model.Rat;
import com.zhen.MySillyDesktopCatGame.Type.RatType;

public class NormalRatFactory extends RatFactory{

    public NormalRatFactory() {
        super(RatType.NORMAL);
    }

    public Rat createRat(){
        int speed = (int)(Math.random()*3)+1;
        int hp = (int)(Math.random()*2)+1;
        int x = GameWindow.GAME_WINDOW_WIDTH-150;
        int y = GameWindow.GAME_WINDOW_HEIGHT - (int)(Math.random()*300)-200;
        return new NormalRat(hp,speed,x,y,generateId());
    }

}
