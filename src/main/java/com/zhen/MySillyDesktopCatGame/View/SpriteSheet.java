package com.zhen.MySillyDesktopCatGame.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private BufferedImage sheet;
    private int spriteSheetRows;
    private int spriteSheetColumns;

    public SpriteSheet(String path, int spriteSheetRows, int spriteSheetColumns) {
        try {
            sheet = ImageIO.read(getClass().getClassLoader().getResource(path));
            this.spriteSheetRows = spriteSheetRows;
            this.spriteSheetColumns = spriteSheetColumns;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int row, int column)
    {
        int spriteWidth = sheet.getWidth()/spriteSheetColumns;
        int spriteHeight = sheet.getHeight()/spriteSheetRows;
        return sheet.getSubimage(row*spriteWidth, column*spriteHeight, spriteWidth, spriteHeight);
    }
}
