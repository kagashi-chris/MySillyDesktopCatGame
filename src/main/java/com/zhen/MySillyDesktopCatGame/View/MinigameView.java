package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Action.DamageRatAction;
import com.zhen.MySillyDesktopCatGame.Action.SwitchScreenToAction;
import com.zhen.MySillyDesktopCatGame.Action.UseSpellOnSlotAction;
import com.zhen.MySillyDesktopCatGame.Controller.Command.Command;
import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Model.Enemy;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.MinigameCat;
import com.zhen.MySillyDesktopCatGame.Model.Rat;
import com.zhen.MySillyDesktopCatGame.Type.AnimalStateType;
import com.zhen.MySillyDesktopCatGame.Type.CatMiniGameStateType;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.Type.RatStateType;
import com.zhen.MySillyDesktopCatGame.Type.SpellSlotType;
import com.zhen.MySillyDesktopCatGame.Util.SpriteUtil;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
    private static Map<AnimalStateType, SpriteUtil.AnimationData> ratSpriteSheetPathTable = new HashMap<>(){{
        put(RatStateType.RUNNING, new SpriteUtil.AnimationData("RatRun.png",1,32,32,1));
    }};
    private HashMap<Rat, AnimatedSprite> ratToSpriteMap = new HashMap<>();
    private HashMap<AnimatedSprite, Rat> spriteToRatMap = new HashMap<>();
    private Set<Rat> ratsToAdd = new HashSet<>();
    private Set<Rat> ratsToDelete = new HashSet<>();
    private Set<Rat> oldRatSet = new HashSet<>();

    private AnimatedSprite catMiniGameAnimatedSprite;
    private JLabel scoreCounterLabel;

    private Command[] spellCommands;
    private List<JButton> spellButtonList = new ArrayList<>();

    private JButton shopButton;
    private JButton spellSlot1;
    private JButton spellSlot2;
    private JButton spellSlot3;
    private JButton spellSlot4;

    private static Map<AnimalStateType, SpriteUtil.AnimationData> catMiniGameSpriteSheetPathTable = new HashMap<>(){{
        put(CatMiniGameStateType.IDLE, new SpriteUtil.AnimationData("CatGunIdle.png",1,50,50,3));
        put(CatMiniGameStateType.SHOOTING, new SpriteUtil.AnimationData("CatGunFire.png",1,50,50,3));
    }};

    public MinigameView(MainController mainController) {
        this.mainController = mainController;
        mainController.subscribe(this);

        spellCommands = gameState.getSpellCommands();

        quitMiniGameButton = new JButton("Leave");
        quitMiniGameButton.setBounds(20,20, 100,20);
        quitMiniGameButton.addActionListener(this);

        shopButton = new JButton("Shop");
        shopButton.setBounds(450,20,100,20);
        shopButton.addActionListener(this);

        scoreCounterLabel = new JLabel("Current Score: " + gameState.getCurrentPoints());
        scoreCounterLabel.setBounds(300,20,100,20);

        catMiniGameAnimatedSprite = SpriteUtil.createAnimatedSprite(catMiniGameSpriteSheetPathTable);

        spellSlot1 = new JButton();
        spellSlot1.setBounds(20,550,100,20);
        spellSlot1.addActionListener(this);

        spellSlot2 = new JButton();
        spellSlot2.setBounds(150,550,100,20);
        spellSlot2.addActionListener(this);

        spellSlot3 = new JButton();
        spellSlot3.setBounds(280,550,100,20);
        spellSlot3.addActionListener(this);

        spellSlot4 = new JButton();
        spellSlot4.setBounds(410,550,100,20);
        spellSlot4.addActionListener(this);

        spellButtonList.add(spellSlot1);
        spellButtonList.add(spellSlot2);
        spellButtonList.add(spellSlot3);
        spellButtonList.add(spellSlot4);

        initView();

    }

    private void initView()
    {
        this.setBackground(Color.lightGray);
        this.add(shopButton);
        this.add(quitMiniGameButton);
        this.add(catMiniGameAnimatedSprite);
        this.add(scoreCounterLabel);
        this.add(spellSlot1);
        this.add(spellSlot2);
        this.add(spellSlot3);
        this.add(spellSlot4);
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
        MinigameCat minigameCat = gameState.getMinigameCat();
        updateSpellSlots();

        catMiniGameAnimatedSprite.draw(minigameCat);

        for(Rat rat: ratsToAdd)
        {
            AnimatedSprite ratAnimatedSprite = SpriteUtil.createAnimatedSprite(ratSpriteSheetPathTable);
            ratToSpriteMap.put(rat, ratAnimatedSprite);
            spriteToRatMap.put(ratAnimatedSprite,rat);
            this.add(ratAnimatedSprite.getjLabel());
            ratAnimatedSprite.getjLabel().addMouseListener(this);
        }
        for(Rat rat: ratsToDelete)
        {
            AnimatedSprite ratAnimatedSprite = ratToSpriteMap.get(rat);
            ratToSpriteMap.get(rat).getjLabel().setVisible(false);
            ratToSpriteMap.remove(rat);
            spriteToRatMap.remove(ratAnimatedSprite);
            this.remove(ratAnimatedSprite.getjLabel());
        }

        for(Map.Entry<Rat,AnimatedSprite> ratEntry : ratToSpriteMap.entrySet())
        {
            ratEntry.getValue().draw(ratEntry.getKey());
        }
        scoreCounterLabel.setText("Points: " + gameState.getCurrentPoints());
        ratsToAdd.clear();
        ratsToDelete.clear();
    }

    private void updateSpellSlots()
    {
        for(int i = 0; i < spellCommands.length; i++)
        {
            if(spellCommands[i] == null)
            {
                spellButtonList.get(i).setText("");
            }
            else
            {
                spellButtonList.get(i).setText(spellCommands[i].getCommandName());
            }
        }
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

        if(e.getActionCommand() == "Leave")
        {
            System.out.println("Leave Pressed");
            mainController.performAction(new SwitchScreenToAction(GameStateType.SILLY_CAT_GAME));
        }
        else if(e.getActionCommand() == "Shop")
        {
            System.out.println("Shop Pressed");
            mainController.performAction(new SwitchScreenToAction(GameStateType.SHOP));
        }
        else if(spellButtonList.contains(e.getSource()))
        {
            System.out.println("Performing spell");
            mainController.performAction(new UseSpellOnSlotAction(SpellSlotType.values()[spellButtonList.indexOf(e.getSource())]));
            System.out.println(spellButtonList.indexOf(e.getSource()));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source =  e.getSource();
        if(source instanceof Enemy)
        {
            Rat rat = spriteToRatMap.get((AnimatedSprite) source);
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
