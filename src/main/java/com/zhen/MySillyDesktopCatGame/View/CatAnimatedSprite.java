package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Type.CatStateType;

import javax.swing.Icon;
import javax.swing.JLabel;

public class CatAnimatedSprite extends AnimatedSprite{

    private final int CAT_IDLE_FRAMES = 4;
    private final int CAT_EAT_FRAMES = 7;
    private final int CAT_DYING_FRAMES = 2;
    private final int CAT_DEAD_FRAMES = 1;
    private final int CAT_DISPLAY_IMAGE_WIDTH = 128;
    private final int CAT_DISPLAY_IMAGE_HEIGHT = 128;
    private int lastDrawnAnimationFrame = 0;
    private CatStateType lastDrawnAnimation = null;
    private boolean lastKnownIsFacingRight = false;

    private Icon[] catDyingIcons;
    private Icon[] catEatingIcons;
    private Icon[] catIdleIcons;
    private Icon[] catDeadIcons;

    public CatAnimatedSprite(Icon[] catDyingIcons, Icon[] catEatingIcons, Icon[] catIdleIcons, Icon[] catDeadIcons) {
        this.catDyingIcons = catDyingIcons;
        this.catEatingIcons = catEatingIcons;
        this.catIdleIcons = catIdleIcons;
        this.catDeadIcons = catDeadIcons;
    }

    public void draw(JLabel spriteComponent, Cat cat) {
        if(lastDrawnAnimation == cat.getCatStateType())
        {
            //TODO no business logic in here
            //draw what you get from the model(can use last known states) to inform your decsion on what frame to draw next
            if(cat.getCatStateType() == CatStateType.IDLE)
            {
                if(cat.isFacingRight() && lastDrawnAnimationFrame == 0)
                {
                    spriteComponent.setIcon(catIdleIcons[1]);
                }
                else if(cat.isFacingRight() && lastDrawnAnimationFrame == 1)
                {
                    spriteComponent.setIcon(catIdleIcons[0]);
                }
                else if(!cat.isFacingRight() && lastDrawnAnimationFrame == 2)
                {
                    spriteComponent.setIcon(catIdleIcons[0]);
                }
            }
        }
    }


    public void incrementAndWrap(Icon[] icons)
    {
        lastDrawnAnimationFrame++;
        if(lastDrawnAnimationFrame > icons.length)
        {
            lastDrawnAnimationFrame = 0;
        }
    }
}
