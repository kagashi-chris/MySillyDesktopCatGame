package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Type.CatMiniGameStateType;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.Map;

public class CatMiniGameAnimatedSprite extends AnimatedSprite{

    private int currentAnimationFrame = 0;
    private CatMiniGameStateType lastDrawnAnimation = null;

    private Map<CatMiniGameStateType, Icon[]> catAnimationTable;


    public CatMiniGameAnimatedSprite(Map<CatMiniGameStateType, Icon[]> catAnimationTable) {
        super(new ImageIcon());
        this.catAnimationTable = catAnimationTable;
    }

    public void draw(JLabel spriteComponent, Cat cat) {
        if(lastDrawnAnimation == cat.getCatMiniGameStateType())
        {
            incrementAndWrap(catAnimationTable.get(cat.getCatMiniGameStateType()));
        }
        else
        {
            currentAnimationFrame = 0;
        }
        spriteComponent.setIcon(catAnimationTable.get(cat.getCatMiniGameStateType())[currentAnimationFrame]);
        lastDrawnAnimation = cat.getCatMiniGameStateType();
    }

    public void incrementAndWrap(Icon[] icons)
    {
        currentAnimationFrame++;
        if(currentAnimationFrame > icons.length - 1)
        {
            currentAnimationFrame = 0;
        }
    }
}
