package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.CatController;
import com.zhen.MySillyDesktopCatGame.Controller.ViewController;
import com.zhen.MySillyDesktopCatGame.Type.CatStateType;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.Util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    private JButton menuButton;
    private JButton feedButton;
    private JButton playButton;
    private JLabel catLabel;
    private ViewController viewController;
    private CatController catController;

    private Sprite[] catIdleSprite;
    private Sprite[] catEatSprite;

    public Game(ViewController viewController, CatController catController) {
        this.viewController = viewController;
        this.catController = catController;

        menuButton = new JButton("Menu");
        menuButton.setBounds(32,20, 100,20);
        menuButton.addActionListener(this);

        feedButton = new JButton("Feed");
        feedButton.setBounds(20,60, 100,20);
        feedButton.addActionListener(this);

        playButton = new JButton("Play");
        playButton.setBounds(20,100, 100,20);
        playButton.addActionListener(this);

//        drawCat();
        catLabel = new JLabel(new ImageIcon("CatIdle.png"));
        catLabel.setBounds(200,200,32,32);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.lightGray);
        this.add(menuButton);
        this.add(feedButton);
        this.add(playButton);
        this.add(catLabel);

        this.setLayout(null);

        Graphics2D g2D = (Graphics2D) g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuButton)
        {
            viewController.setGameState(GameStateType.MENU);
        }
        if(e.getSource() == feedButton)
        {
            catController.feedCat();
            System.out.println(catController.getCat().getFullness());
        }
    }

    //lazy load the cat sprite and save all the animation to catIdleSprite
    public void drawCatIdle()
    {
        SpriteSheet catIdleSpriteSheet = new SpriteSheet("CatIdle.png",1,1);
        catIdleSprite = new Sprite[4];
        FileUtil.fillSpriteList(catIdleSpriteSheet,catIdleSprite,32,32);
    }

    //lazy load the cat sprite and save all the animation to catEatSprite
    public void drawCatEat()
    {
//        SpriteSheet catEatSpriteSheet = new SpriteSheet("CatSpriteSheet.png",2,7);
//        catEatSprite = new Sprite[7];
//        FileUtil.fillSpriteList(catEatSpriteSheet,catEatSprite);
    }

    public void drawCat()
    {
        System.out.println("running draw cat");
        if(catController.getCat().getCatStateType().equals(CatStateType.IDLE))
        {
            if(catIdleSprite == null)
            {
                System.out.println("draw cat idle");
                drawCatIdle();
            }
        }
        else if(catController.getCat().getCatStateType().equals(CatStateType.EATING))
        {
            if(catEatSprite == null)
            {
                drawCatEat();
            }
        }
    }
}
