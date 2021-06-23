package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuView extends JPanel implements ActionListener{

    private static MenuView instance;
    private MainController mainController;
    private JButton playButton;
    private JButton exitButton;
    private JLabel title;
    private Image titleImage;

    public MenuView(MainController mainController) {
        this.mainController = mainController;

        playButton = new JButton("Play");
        playButton.setBounds(20,20, 100,20);
        playButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setBounds(20,60, 100,20);
        exitButton.addActionListener(this);

        title = new JLabel();
        initTitle();
        title.setIcon(new ImageIcon(titleImage));
        title.setBounds(250,50,300,300);
    }

    public void initTitle()
    {
        try {
            titleImage = ImageIO.read(getClass().getClassLoader().getResource("MySillyDesktopCatTitle.png"));
            titleImage.getScaledInstance(300,300,1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized MenuView getInstance(MainController mainController)
    {
        if(instance == null)
        {
            instance = new MenuView(mainController);
        }
        return instance;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.LIGHT_GRAY);
        this.add(playButton);
        this.add(exitButton);
        this.add(title);


        this.setLayout(null);

        Graphics2D g2D = (Graphics2D) g;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton)
        {
            System.out.println("PLAY!");
            mainController.setGameState(GameStateType.SILLY_CAT_GAME);

        }
        else if(e.getSource() == exitButton)
        {
            System.out.println("EXIT!");
            mainController.setGameState(GameStateType.EXIT);
        }
    }
}
