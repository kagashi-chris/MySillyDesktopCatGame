package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.ViewController;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    private JButton menuButton;
    private ViewController viewController;

    public Game(ViewController viewController) {
        this.viewController = viewController;

        menuButton = new JButton("Menu");
        menuButton.setBounds(20,20, 100,20);
        menuButton.addActionListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.RED);
        this.add(menuButton);


        this.setLayout(null);

        Graphics2D g2D = (Graphics2D) g;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuButton)
        {
            viewController.setGameState(GameStateType.MENU);
        }
    }
}
