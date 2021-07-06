package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Action.SwitchScreenToAction;
import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.Type.RatStateType;
import com.zhen.MySillyDesktopCatGame.Util.SpriteUtil;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MinigameView extends JPanel implements View, ActionListener {

    private MainController mainController;
    private static MinigameView instance;
    private GameState gameState;
    private JButton quitMiniGameButton;
    private static Map<RatStateType, SpriteUtil.AnimationData> ratStateTypeAnimationDataMap = new HashMap<>(){{
        put(RatStateType.RUNNING, new SpriteUtil.AnimationData("RatRun.png",1,32,32,2));
        put(RatStateType.ATTACKING, new SpriteUtil.AnimationData("RatRun.png",1,32,32,2));
        put(RatStateType.DEAD, new SpriteUtil.AnimationData("RatRun.png",1,32,32,2));
    }};


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
        switch(e.getActionCommand())
        {
            case "Leave":
                System.out.println("Leave PRESSED");
                mainController.performAction(new SwitchScreenToAction(GameStateType.SILLY_CAT_GAME));
                break;

            default:
                break;
        }
    }
}
