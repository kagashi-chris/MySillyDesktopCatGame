package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Util.SpriteUtil;

import javax.swing.Icon;
import javax.swing.JLabel;

public abstract class AnimatedSprite {

    private static Icon defaultIcon = SpriteUtil.getImageIconFromFile("ERROR.png");

    public void draw(JLabel spriteComponent)
    {
        spriteComponent.setIcon(defaultIcon);
    }
}
