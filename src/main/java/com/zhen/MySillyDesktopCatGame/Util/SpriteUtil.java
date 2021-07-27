package com.zhen.MySillyDesktopCatGame.Util;

import com.zhen.MySillyDesktopCatGame.Type.AnimalStateType;
import com.zhen.MySillyDesktopCatGame.View.AnimatedSprite;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpriteUtil {

    public static final int DEFAULT_SCALE_FACTOR = 4;

    public static Icon getImageIconFromFile(String filePath)
    {
        ImageIcon imageIcon = null;
        try
        {
            System.out.printf("Filepath: %s", filePath);
            imageIcon = new ImageIcon(ImageIO.read(SpriteUtil.class.getClassLoader().getResource(filePath)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return imageIcon;
    }

    public static BufferedImage getImage(String filePath)
    {
        BufferedImage image;
        try {
            image = ImageIO.read(SpriteUtil.class.getClassLoader().getResource(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return image;
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

    public static AnimatedSprite createAnimatedSprite(Map<AnimalStateType, AnimationData> spriteSheetPathTable)
    {
        Map<AnimalStateType, Icon[]> animationTable = new HashMap<>();
        for(Map.Entry<AnimalStateType, AnimationData> entry: spriteSheetPathTable.entrySet())
        {
            animationTable.put(entry.getKey(), getImageIconsFromSpriteSheet(
                    entry.getValue().getSpriteSheetPath(),
                    entry.getValue().getNumAnimationFrames(),
                    entry.getValue().getPixelWidth(),
                    entry.getValue().getPixelHeight(),
                    entry.getValue().getScaleFactor()
            ));
        }
        AnimatedSprite animatedSprite = new AnimatedSprite(animationTable);
        return animatedSprite;
    }

    public static class AnimationData{
        private String spriteSheetPath;
        private int numAnimationFrames;
        private int pixelWidth;
        private int pixelHeight;
        private int scaleFactor;

        public AnimationData(String spriteSheetPath, int numAnimationFrames, int pixelWidth, int pixelHeight, int scaleFactor) {
            this.spriteSheetPath = spriteSheetPath;
            this.numAnimationFrames = numAnimationFrames;
            this.pixelWidth = pixelWidth;
            this.pixelHeight = pixelHeight;
            this.scaleFactor = scaleFactor;
        }

        public String getSpriteSheetPath() {
            return spriteSheetPath;
        }

        public void setSpriteSheetPath(String spriteSheetPath) {
            this.spriteSheetPath = spriteSheetPath;
        }

        public int getNumAnimationFrames() {
            return numAnimationFrames;
        }

        public void setNumAnimationFrames(int numAnimationFrames) {
            this.numAnimationFrames = numAnimationFrames;
        }

        public int getPixelWidth() {
            return pixelWidth;
        }

        public void setPixelWidth(int pixelWidth) {
            this.pixelWidth = pixelWidth;
        }

        public int getPixelHeight() {
            return pixelHeight;
        }

        public void setPixelHeight(int pixelHeight) {
            this.pixelHeight = pixelHeight;
        }

        public int getScaleFactor() {
            return scaleFactor;
        }

        public void setScaleFactor(int scaleFactor) {
            this.scaleFactor = scaleFactor;
        }
    }
}

