package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinigameView extends JPanel implements View, ActionListener {

    private MainController mainController;
    private static MinigameView instance;
    private GameState gameState;
    private JButton quitMiniGameButton;

    public MinigameView(MainController mainController) {
        this.mainController = mainController;
        this.gameState = mainController.getGameState();

        quitMiniGameButton = new JButton("Leave");
        quitMiniGameButton.setBounds(20,20, 100,20);
        quitMiniGameButton.addActionListener(this);

        initView();
        mainController.subscribe(this);
    }

    private void initView()
    {
        this.setBackground(Color.lightGray);
        this.add(quitMiniGameButton);
        this.setLayout(null);
    }

    public static synchronized MinigameView getInstance(MainController mainController)
    {
        if(instance == null)
        {
            instance = new MinigameView(mainController);
        }
        return instance;
    }

    @Override
    public void tick() {

    }

    @Override
    public void updateView(GameState gameState) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
