package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Action.DamageRatAction;
import com.zhen.MySillyDesktopCatGame.Action.SwitchScreenToAction;
import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.Rat;
import com.zhen.MySillyDesktopCatGame.Type.CatMiniGameStateType;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.Type.RatStateType;
import com.zhen.MySillyDesktopCatGame.Util.SpriteUtil;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinigameView extends JPanel implements View, ActionListener, MouseListener {

    private MainController mainController;
    private static MinigameView instance;
    private GameState gameState;
    private JButton quitMiniGameButton;
    private static Map<RatStateType, SpriteUtil.AnimationData> ratSpriteSheetPathTable = new HashMap<>(){{
        put(RatStateType.RUNNING, new SpriteUtil.AnimationData("RatRun.png",1,32,32,1));
    }};
    private RatAnimatedSprite ratAnimatedSprite;
    private HashMap<Rat, RatAnimatedSprite> ratToSpriteMap = new HashMap<>();
    private HashMap<RatAnimatedSprite, Rat> spriteToRatMap = new HashMap<>();
    private Set<Rat> ratsToAdd = new HashSet<>();
    private Set<Rat> ratsToDelete = new HashSet<>();
    private Set<Rat> oldRatSet = new HashSet<>();

    private JLabel catMiniGameLabel;
    private CatMiniGameAnimatedSprite catMiniGameAnimatedSprite;
    private final int CAT_DISPLAY_IMAGE_WIDTH = 150;
    private final int CAT_DISPLAY_IMAGE_HEIGHT = 150;

    private static Map<CatMiniGameStateType, SpriteUtil.AnimationData> catMiniGameSpriteSheetPathTable = new HashMap<>(){{
        put(CatMiniGameStateType.IDLE, new SpriteUtil.AnimationData("CatGunIdle.png",1,50,50,3));
        put(CatMiniGameStateType.SHOOTING, new SpriteUtil.AnimationData("CatGunFire.png",1,50,50,3));
    }};

    public MinigameView(MainController mainController) {
        this.mainController = mainController;
        this.gameState = mainController.getGameState();

        quitMiniGameButton = new JButton("Leave");
        quitMiniGameButton.setBounds(20,20, 100,20);
        quitMiniGameButton.addActionListener(this);

        ratAnimatedSprite = SpriteUtil.createRatAnimatedSprite(ratSpriteSheetPathTable);

        catMiniGameLabel = new JLabel(new ImageIcon());
        catMiniGameAnimatedSprite = SpriteUtil.createCatMiniGameAnimatedSprite(catMiniGameSpriteSheetPathTable);
        catMiniGameLabel.setBounds(50,250,CAT_DISPLAY_IMAGE_WIDTH,CAT_DISPLAY_IMAGE_HEIGHT);

        initView();
        mainController.subscribe(this);
    }

    private void initView()
    {
        this.setBackground(Color.lightGray);
        this.add(quitMiniGameButton);
        this.add(catMiniGameLabel);
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
        List<Cat> catList = gameState.getCatList();
        for(Cat cat:catList)
        {
            catMiniGameAnimatedSprite.draw(catMiniGameLabel,cat);
        }
        for(Rat rat: ratsToAdd)
        {
            RatAnimatedSprite ratAnimatedSprite = SpriteUtil.createRatAnimatedSprite(ratSpriteSheetPathTable);
            ratToSpriteMap.put(rat, ratAnimatedSprite);
            spriteToRatMap.put(ratAnimatedSprite,rat);
            this.add(ratAnimatedSprite.getjLabel());
            ratAnimatedSprite.getjLabel().addMouseListener(this);
        }
        for(Rat rat: ratsToDelete)
        {
            RatAnimatedSprite ratAnimatedSprite = ratToSpriteMap.get(rat);

            ratToSpriteMap.remove(rat);
            spriteToRatMap.remove(ratAnimatedSprite);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source =  e.getSource();
        if(source instanceof RatAnimatedSprite)
        {
            Rat rat = spriteToRatMap.get((RatAnimatedSprite)source);
            mainController.performAction(new DamageRatAction(rat));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
