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
    private JLabel jLabel;

    private Map<RatStateType, Icon[]> ratAnimationTable;


    public RatAnimatedSprite(Map<RatStateType, Icon[]> ratAnimationTable) {
        this.ratAnimationTable = ratAnimationTable;
        this.jLabel = new JLabel(new ImageIcon());

    }

    public void draw(Rat rat) {
        jLabel.setBounds(rat.getX(), rat.getY(), 128,128);
        if(lastDrawnAnimation == rat.getRatStateType())
        {
            incrementAndWrap(ratAnimationTable.get(rat.getRatStateType()));
        }
        else
        {
            currentAnimationFrame = 0;
        }
        System.out.println("Current animation frame: " + currentAnimationFrame);
        jLabel.setIcon(ratAnimationTable.get(rat.getRatStateType())[currentAnimationFrame]);
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
        return jLabel;
    }
}
