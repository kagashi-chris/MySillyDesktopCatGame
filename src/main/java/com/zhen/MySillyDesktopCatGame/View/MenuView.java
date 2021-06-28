package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Controller.MenuController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JPanel implements View, ActionListener {

    private static MenuView instance;
    private MainController mainController;
    private MenuController menuController;
    private JButton playButton;
    private JButton exitButton;
    private JLabel title;
    private Image titleImage;

    public MenuView(MainController mainController) {
        this.mainController = mainController;
        menuController = mainController.getMenuController();

        playButton = new JButton("Play");
        playButton.setBounds(20,20, 100,20);
        playButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setBounds(20,60, 100,20);
        exitButton.addActionListener(this);

//        title = new JLabel();
//        title.setIcon(new ImageIcon(titleImage));
//        title.setBounds(250,50,300,300);
        initView();
        mainController.subscribe(this);
    }

    public void initView()
    {
//        try {
//            titleImage = ImageIO.read(getClass().getClassLoader().getResource("MySillyDesktopCatTitle.png"));
//            titleImage.getScaledInstance(300,300,1);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        this.setBackground(Color.LIGHT_GRAY);
        this.add(playButton);
        this.add(exitButton);
        this.add(title);
        this.setLayout(null);
    }

    public static synchronized MenuView getInstance(MainController mainController)
    {
        if(instance == null)
        {
            instance = new MenuView(mainController);
        }
        return instance;
    }

    @Override
    public void updateView(GameState gameState) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
