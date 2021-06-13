package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.View.Sprite;
import com.zhen.MySillyDesktopCatGame.View.SpriteSheet;

public class SpriteController {

    public void fillSpriteList(SpriteSheet spriteSheet, Sprite[] sprite)
    {
        for(int i = 0; i < sprite.length; i++)
        {
            sprite[i] = new Sprite(spriteSheet, i,1);
        }
    }
}
