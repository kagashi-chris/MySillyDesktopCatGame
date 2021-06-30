package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Type.CatStateType;

import javax.swing.Icon;
import javax.swing.JLabel;
import java.util.Map;

public class CatAnimatedSprite extends AnimatedSprite{

    private int currentAnimationFrame = 0;
    private CatStateType lastDrawnAnimation = null;

    private Map<CatStateType, Icon[]> catAnimationTable;


    public CatAnimatedSprite(Map<CatStateType, Icon[]> catAnimationTable) {
        this.catAnimationTable = catAnimationTable;
    }

    public void draw(JLabel spriteComponent, Cat cat) {
        if(lastDrawnAnimation == cat.getCatStateType())
        {
            incrementAndWrap(catAnimationTable.get(cat.getCatStateType()));
        }
        else
        {
            currentAnimationFrame = 0;
        }
        spriteComponent.setIcon(catAnimationTable.get(cat.getCatStateType())[currentAnimationFrame]);
    }

    public void incrementAndWrap(Icon[] icons)
    {
        currentAnimationFrame++;
        if(currentAnimationFrame > icons.length)
        {
            currentAnimationFrame = 0;
        }
    }
}
