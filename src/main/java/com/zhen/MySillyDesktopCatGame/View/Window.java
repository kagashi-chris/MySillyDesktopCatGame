package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Model.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends Canvas {

    public Window(GameWindow gameWindow) {
        Dimension size = new Dimension(gameWindow.getGameWindowWidth(), gameWindow.getGameWindowHeight());
        setPreferredSize(size);
        setMaximumSize(size);
        setMaximumSize(size);
    }

    public void createFrame(Window window)
    {
        JFrame frame = new JFrame();
        frame.add(window);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void render(Window window){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null)
        {
            window.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(210,210,210));
        g.fillRect(0,0,getWidth(),getHeight());

        g.dispose();
        bs.show();
    }




}
