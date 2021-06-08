package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class Menu implements ActionListener{

    private ViewController viewController;


    public Menu(ViewController viewController) {
        this.viewController = viewController;
    }

    public void createMenuView(JFrame frame, Graphics g, Window window)
    {
        g.setColor(new Color(230,230,230));
        g.fillRect(0,0, window.getWidth(), window.getHeight());
        JButton playButton = createButton("Play",10,110,80,25);
        playButton.addActionListener(this);



    }

    private JButton createButton(String text, int x, int y, int width, int height)
    {
        JButton jButton = new JButton();
        jButton.setText(text);
        jButton.setBounds(x,y,width,height);
        return jButton;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
