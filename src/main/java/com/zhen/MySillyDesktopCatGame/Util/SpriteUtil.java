package com.zhen.MySillyDesktopCatGame.Util;

import com.zhen.MySillyDesktopCatGame.View.CatAnimatedSprite;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteUtil {

    public static final int DEFAULT_SCALE_FACTOR = 4;

    public static Icon getImageIconFromFile(String filePath)
    {
        ImageIcon imageIcon = null;
        try
        {
            imageIcon = new ImageIcon(ImageIO.read(SpriteUtil.class.getClassLoader().getResource(filePath)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return imageIcon;
    }

    private static Icon[] getImageIconsFromSpriteSheet(String spriteSheetPath, int numAnimationFrames, int pixelWidth, int pixelHeight, int scaleFactor)
    {
        ImageIcon[] imageIcons = new ImageIcon[numAnimationFrames];
        try {
            BufferedImage spriteSheet = ImageIO.read(SpriteUtil.class.getClassLoader().getResource(spriteSheetPath));
            for(int i = 0; i < numAnimationFrames; i++)
            {
                Image tempImage = spriteSheet.getSubimage(i*32,0, pixelWidth,pixelHeight);
                imageIcons[i] = new ImageIcon(tempImage.getScaledInstance(pixelWidth*scaleFactor,pixelHeight*scaleFactor,Image.SCALE_SMOOTH));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageIcons;
    }

    public static CatAnimatedSprite createCatAnimatedSprite(String spriteSheetPath1, String spritePath2, String spritePath3, String spritePath4, int numAnimationFrames, int pixelWidth, int pixelHeight, int scaleFactor)
    {
        //TODO change to take in a list of sprite path instead
        Icon[] imageIcons = getImageIconsFromSpriteSheet(spriteSheetPath1,numAnimationFrames,pixelWidth,pixelHeight,scaleFactor);
        Icon[] imageIcons2 = getImageIconsFromSpriteSheet(spriteSheetPath1,numAnimationFrames,pixelWidth,pixelHeight,scaleFactor);
        Icon[] imageIcons3 = getImageIconsFromSpriteSheet(spriteSheetPath1,numAnimationFrames,pixelWidth,pixelHeight,scaleFactor);
        Icon[] imageIcons4 = getImageIconsFromSpriteSheet(spriteSheetPath1,numAnimationFrames,pixelWidth,pixelHeight,scaleFactor);
        CatAnimatedSprite catAnimatedSprite = new CatAnimatedSprite(imageIcons, imageIcons2, imageIcons3, imageIcons4);
        return catAnimatedSprite;
    }
}
