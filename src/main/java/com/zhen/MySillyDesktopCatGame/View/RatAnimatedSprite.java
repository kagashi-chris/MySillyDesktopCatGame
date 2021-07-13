package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Model.Rat;
import com.zhen.MySillyDesktopCatGame.Type.RatStateType;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.Map;

public class RatAnimatedSprite extends AnimatedSprite{


    private int currentAnimationFrame = 0;
    private RatStateType lastDrawnAnimation = null;

    private Map<RatStateType, Icon[]> ratAnimationTable;


    public RatAnimatedSprite(Map<RatStateType, Icon[]> ratAnimationTable) {
        super(new ImageIcon());
        this.ratAnimationTable = ratAnimationTable;
    }

    public void draw(Rat rat) {
        this.setBounds(rat.getX(), rat.getY(), 128,128);
        if(lastDrawnAnimation == rat.getRatStateType())
        {
            incrementAndWrap(ratAnimationTable.get(rat.getRatStateType()));
        }
        else
        {
            currentAnimationFrame = 0;
        }
        this.setIcon(ratAnimationTable.get(rat.getRatStateType())[currentAnimationFrame]);
        lastDrawnAnimation = rat.getRatStateType();
    }

    public void incrementAndWrap(Icon[] icons)
    {
        currentAnimationFrame++;
        if(currentAnimationFrame > icons.length - 1)
        {
            currentAnimationFrame = 0;
        }
    }

    public JLabel getjLabel() {
        return this;
    }
}
