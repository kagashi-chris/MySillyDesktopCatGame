package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.ViewController;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class Menu extends JPanel implements ActionListener{

    private JButton playButton;
    private JButton exitButton;
    private ViewController viewController;

    public Menu(ViewController viewController) {
        this.viewController = viewController;

        playButton = new JButton("Play");
        playButton.setBounds(20,20, 100,20);
        playButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setBounds(20,60, 100,20);
        exitButton.addActionListener(this);


    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.BLUE);
        this.add(playButton);
        this.add(exitButton);


        this.setLayout(null);

        Graphics2D g2D = (Graphics2D) g;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton)
        {
            System.out.println("PLAY!");
            viewController.setGameState(GameStateType.GAME);
        }
        else if(e.getSource() == exitButton)
        {
            System.out.println("EXIT!");
            viewController.setGameState(GameStateType.EXIT);
        }
    }
}
