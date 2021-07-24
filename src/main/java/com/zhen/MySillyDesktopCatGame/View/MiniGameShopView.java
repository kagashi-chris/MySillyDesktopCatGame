package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Action.BuyItemAction;
import com.zhen.MySillyDesktopCatGame.Action.SwitchScreenToAction;
import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.Type.SpellType;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniGameShopView extends JPanel implements View, ActionListener {

    private MainController mainController;
    private GameState gameState;
    private JButton returnToGame;
    private JLabel scoreCounterLabel;
    private static MiniGameShopView instance;
    private JButton buyFireBall;
    private JButton buyLightning;
    private JButton buyFreeze;

    public MiniGameShopView(MainController mainController) {
        this.mainController = mainController;
        mainController.subscribe(this);

        returnToGame = new JButton("Return");
        returnToGame.setBounds(20,20, 100,20);
        returnToGame.addActionListener(this);

        scoreCounterLabel = new JLabel("Current Score: " + gameState.getCurrentPoints());
        scoreCounterLabel.setBounds(300,20,100,20);

        buyFireBall = new JButton("Fireball");
        buyFireBall.setBounds(20,100,100,20);
        buyFireBall.addActionListener(this);

        buyLightning = new JButton("Lightning");
        buyLightning.setBounds(20,140,100,20);
        buyLightning.addActionListener(this);

        buyFreeze = new JButton("Freeze");
        buyFreeze.setBounds(20,180,100,20);
        buyFreeze.addActionListener(this);

        initView();

    }

    private void initView()
    {
        this.setBackground(Color.lightGray);
        this.add(returnToGame);
        this.add(buyFireBall);
        this.add(buyLightning);
        this.add(buyFreeze);
        this.setLayout(null);
    }

    public static synchronized MiniGameShopView getInstance(MainController mainController)
    {
        if(instance == null)
        {
            instance = new MiniGameShopView(mainController);
        }
        return instance;
    }

    @Override
    public void tick() {
        scoreCounterLabel.setText("Current Score: " + gameState.getCurrentPoints());
    }

    @Override
    public void updateView(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand())
        {
            case "Return":
                System.out.println("Leave Pressed");
                mainController.performAction(new SwitchScreenToAction(GameStateType.MINIGAME_1));
                break;

            case "Fireball":
                System.out.println("Buying Fireball");
                mainController.performAction(new BuyItemAction(SpellType.FIREBALL));
                break;

            case "Freeze":
                System.out.println("Buying Freeze");
                mainController.performAction(new BuyItemAction(SpellType.FREEZE));
                break;

            default:
                break;
        }
    }
}
