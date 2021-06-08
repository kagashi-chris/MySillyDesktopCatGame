package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.ViewController;
import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class Window extends Canvas {
//
//    private ViewController viewController;
//    private JFrame frame;
//    private Menu menu;
//
//
//
//    public Window(GameWindow gameWindow) {
//        frame = new JFrame();
//        frame.add(this);
//        frame.setSize(gameWindow.getGameWindowWidth(),gameWindow.getGameWindowHeight());
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        JButton button = new JButton();
//        button.setBounds(20,20,20,20);
//        frame.add(this);
//
//
//    }
//
//    public void createFrame()
//    {
//
//    }
//
//    public void subscribe(ViewController viewController)
//    {
//        this.viewController = viewController;
//    }
//
//    @Override
//    public void update(Window window, GameStateType gameStateType)
//    {
//        render(window, gameStateType);
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//    }
//
//    public void render(Window window, GameStateType gameStateType){
//        BufferStrategy bs = getBufferStrategy();
//        if(bs == null)
//        {
//            this.createBufferStrategy(3);
//            return;
//        }
//        Graphics g = bs.getDrawGraphics();
//
//        if(gameStateType.equals(GameStateType.MENU))
//        {
//            JButton jButton = new JButton();
//            jButton.setBounds(20,20,20,20);
//            frame.add(jButton);
//        }
//
//        frame.setVisible(true);
//        g.dispose();
//        bs.show();
//    }




}
