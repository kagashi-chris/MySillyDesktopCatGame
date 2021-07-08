package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Action.SwitchScreenToAction;
import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.Rat;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.Type.RatStateType;
import com.zhen.MySillyDesktopCatGame.Util.SpriteUtil;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinigameView extends JPanel implements View, ActionListener {

    private MainController mainController;
    private static MinigameView instance;
    private GameState gameState;
    private JButton quitMiniGameButton;
    private static Map<RatStateType, SpriteUtil.AnimationData> ratSpriteSheetPathTable = new HashMap<>(){{
        put(RatStateType.RUNNING, new SpriteUtil.AnimationData("RatRun.png",1,32,32,2));
    }};
    private RatAnimatedSprite ratAnimatedSprite;
    private HashMap<Rat, RatAnimatedSprite> ratToSpriteMap = new HashMap<>();
    private Set<Rat> ratsToAdd = new HashSet<>();
    private Set<Rat> ratsToDelete = new HashSet<>();
    private Set<Rat> oldRatSet = new HashSet<>();

    public MinigameView(MainController mainController) {
        this.mainController = mainController;
        this.gameState = mainController.getGameState();

        quitMiniGameButton = new JButton("Leave");
        quitMiniGameButton.setBounds(20,20, 100,20);
        quitMiniGameButton.addActionListener(this);

        ratAnimatedSprite = SpriteUtil.createRatAnimatedSprite(ratSpriteSheetPathTable);

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
        for(Rat rat: ratsToAdd)
        {
            RatAnimatedSprite ratAnimatedSprite = SpriteUtil.createRatAnimatedSprite(ratSpriteSheetPathTable);
            ratToSpriteMap.put(rat, ratAnimatedSprite);
            this.add(ratAnimatedSprite.getjLabel());
        }
        for(Rat rat: ratsToDelete)
        {
            RatAnimatedSprite ratAnimatedSprite = ratToSpriteMap.get(rat);
            ratToSpriteMap.remove(rat);
            this.remove(ratAnimatedSprite.getjLabel());
        }

        for(Map.Entry<Rat,RatAnimatedSprite> ratEntry : ratToSpriteMap.entrySet())
        {
            ratEntry.getValue().draw(ratEntry.getKey());
        }
        ratsToAdd.clear();
        ratsToDelete.clear();
    }

    @Override
    public void updateView(GameState gameState)
    {
        this.gameState = gameState;
        System.out.println("Updating minigame view");
        Set<Rat> newRatSet = gameState.getRatSet();
        ratsToAdd = new HashSet<>(newRatSet);
        ratsToAdd.removeAll(oldRatSet);
        ratsToDelete = new HashSet<>(oldRatSet);
        ratsToDelete.removeAll(newRatSet);
        oldRatSet = new HashSet<>(this.gameState.getRatSet());
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
