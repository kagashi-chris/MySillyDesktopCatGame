package com.zhen.MySillyDesktopCatGame.Util;

import com.zhen.MySillyDesktopCatGame.View.AnimatedCatSprite;
import com.zhen.MySillyDesktopCatGame.View.AnimatedSprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteUtil {

    public static final String CAT_IDLE_ANIMATION_PATH = "CatIdle.png";
    public static final int CAT_IDLE_FRAMES = 4;
    private final int CAT_DISPLAY_IMAGE_WIDTH = 256;
    private final int CAT_DISPLAY_IMAGE_HEIGHT = 256;
    private final int CAT_PIXEL_WIDTH = 32;
    private final int CAT_PIXEL_HEIGHT = 32;

    //lazy load the cat sprite and save all the animation to catIdleSprite list
    //There are currently 4 Sprite images each being 32 x 32 pixels
    //used a for loop to get the sub images inside the sprite sheet
    //tempImage uses scaleUpImage method and scales up the 32 x 32 pixel art to appear larger on screen
    public AnimatedSprite createAnimatedSprite(String spriteSheetPath, int numAnimationFrames, int pixelWidth, int pixelHeight, int displayPixelWidth, int displayPixelHeight)
    {
        Image[] images = new Image[numAnimationFrames];
        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getClassLoader().getResource(spriteSheetPath));
            for(int i = 0; i < numAnimationFrames; i++)
            {
                Image tempImage = spriteSheet.getSubimage(i*32,0, pixelWidth,pixelHeight);
                images[i] = scaleUpImage(displayPixelWidth, displayPixelHeight, tempImage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        AnimatedSprite animatedCatSprite = new AnimatedCatSprite(images);
        return animatedCatSprite;
    }

    public Image scaleUpImage(int scaleWidth, int scaleHeight, Image image)
    {
        return image.getScaledInstance(scaleWidth,scaleHeight,Image.SCALE_SMOOTH);

    }
}
