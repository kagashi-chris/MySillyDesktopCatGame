package com.zhen.MySillyDesktopCatGame.View;

import java.awt.*;

public abstract class AnimatedSprite {

    private Image[] images;
    private int counter = 0;

    public AnimatedSprite(Image[] images) {
        this.images = images;
    }

    public Image getCurrentFrame()
    {
        return images[counter];
    }

    //advance the animation by one frame
    public void next()
    {
        if(counter<images.length)
        {
            counter++;
        }
        else{
            counter = 0;
        }
    }
}
