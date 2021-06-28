package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Action.FeedAction;
import com.zhen.MySillyDesktopCatGame.Action.MakeHungryDebugAction;
import com.zhen.MySillyDesktopCatGame.Action.SwitchScreenToAction;
import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Type.CatStateType;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SillyCatGameView extends JPanel implements ActionListener, View{

    private static SillyCatGameView sillyCatGameView;

    private JButton menuButton;
    private JButton feedButton;
    private JButton playButton;

    //TODO REMOVE WHEN DONE
    private JButton debugHungryButton;
    private java.util.List<JButton> buttonList = new ArrayList<>();

    private JLabel catLabel;
    private MainController mainController;
    private static SillyCatGameView instance;

    private Cat cat;

//    private BufferedImage catIdleSpriteSheet;
//    private BufferedImage catEatSpriteSheet;
//    private BufferedImage catDyingSpriteSheet;
//    private BufferedImage catDeadSpriteSheet;
//    private BufferedImage heartSpriteSheet;
//
//    private Image[] catIdleSprite;
//    private Image[] catEatSprite;
//    private Image[] catDyingSprite;
//    private Image[] catDeadSprite;
//    private Image[] heartSprite;
//
//    private final int CAT_IDLE_FRAMES = 4;
//    private final int CAT_EAT_FRAMES = 7;
//    private final int CAT_DYING_FRAMES = 2;
//    private final int CAT_DEAD_FRAMES = 1;
    private final int CAT_DISPLAY_IMAGE_WIDTH = 128;
    private final int CAT_DISPLAY_IMAGE_HEIGHT = 128;

    private boolean facingRight = true;
    private int index = 0;
    int time = 0;

    //this class manages the display of Game elements within the frame. it constructs the buttons/graphics.
    //Action listeners are placed on the buttons so view controller and/or cat controller can be notifed when
    //certain buttons are pressed. The animation of the cat changes on a timer. Every one second the timer will call
    //the action listener and check the CatStateType again. If it remains the same then it moves onto the next frame
    //of the animation, else it resets the frame count back to 0 and play the request animation.
    private SillyCatGameView(MainController mainController)
    {
        this.mainController = mainController;

        menuButton = new JButton("Menu");
        menuButton.setBounds(20,20, 100,20);
        menuButton.addActionListener(this);

        feedButton = new JButton("Feed");
        feedButton.setBounds(20,60, 100,20);
        feedButton.addActionListener(this);

        playButton = new JButton("Play");
        playButton.setBounds(20,100, 100,20);
        playButton.addActionListener(this);

        //TODO REMOVE WHEN DONE
        debugHungryButton = new JButton("Hungry");
        debugHungryButton.setBounds(20,140, 100,20);
        debugHungryButton.addActionListener(this);

        buttonList.add(playButton);
        buttonList.add(feedButton);
        buttonList.add(debugHungryButton);
        buttonList.add(menuButton);

        catLabel = new JLabel(new ImageIcon());
        catLabel.setBounds(150,150,CAT_DISPLAY_IMAGE_WIDTH,CAT_DISPLAY_IMAGE_HEIGHT);
        initView();
        mainController.subscribe(this);
    }


    public static synchronized SillyCatGameView getInstance(MainController mainController)
    {
        if(instance == null)
        {
            instance = new SillyCatGameView(mainController);
        }
        return instance;
    }

    private void initView()
    {
        this.setBackground(Color.lightGray);
        this.add(menuButton);
        this.add(feedButton);
        this.add(playButton);
        this.add(debugHungryButton);
        this.add(catLabel);
        this.setLayout(null);
    }

    private void displayCatStateType(CatStateType catStateType)
    {

    }

    @Override
    public void updateView(GameState gameState)
    {
        List<Cat> catList = gameState.getCatList();
        for(Cat cat:catList)
        {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand())
        {
            case "Menu":
                System.out.println("MENU PRESSED");
                mainController.performAction(new SwitchScreenToAction(GameStateType.MENU));
                break;

            case "Feed":
                System.out.println("FEED PRESSED");
                mainController.performAction(new FeedAction(cat));
                break;

            case "Hungry":
                System.out.println("HUNGRY PRESSED");
                mainController.performAction(new MakeHungryDebugAction(cat));
                break;

            case "Play":
                System.out.println("PLAY PRESSED");
                mainController.performAction(new SwitchScreenToAction(GameStateType.MINIGAME_1));
                break;

            default:
                break;
        }
    }


}
