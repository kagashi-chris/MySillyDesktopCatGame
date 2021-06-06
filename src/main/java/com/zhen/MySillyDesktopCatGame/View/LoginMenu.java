package com.zhen.MySillyDesktopCatGame.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

//public class LoginMenu implements ActionListener {
//
//    public LoginMenu() {
//
//        this.viewController = viewController;
//        this.window = window;
//        jPanel = window.getJPanel();
//        frame = window.getJFrame();
//
//        playButton = createButton("Play",10,110,80,25);
//        playButton.addActionListener(this);
//        subscribe(viewController);
//
//        frame.setVisible(true);
//
//    }
//
//    private JButton createButton(String text, int x, int y, int width, int height)
//    {
//        JButton jButton = new JButton();
//        jButton.setText(text);
//        jButton.setBounds(x,y,width,height);
//        jPanel.add(jButton);
//        return jButton;
//    }
//
//
//    private void subscribe(ViewController viewController)
//    {
//        this.viewController = viewController;
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == playButton)
//        {
//            System.out.println("PLAY!");
//        }
//    }
//}
