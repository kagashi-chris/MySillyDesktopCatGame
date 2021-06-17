package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Controller.CatController;
import com.zhen.MySillyDesktopCatGame.Controller.ViewController;
import com.zhen.MySillyDesktopCatGame.Type.CatStateType;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener {

    private JButton menuButton;
    private JButton feedButton;
    private JButton playButton;

    //TODO REMOVE WHEN DONE
    private JButton debugHungryButton;
    private java.util.List<JButton> buttonList = new ArrayList<>();

    private JLabel catLabel;
    private ViewController viewController;
    private CatController catController;

    private BufferedImage catIdleSpriteSheet;
    private BufferedImage catEatSpriteSheet;
    private BufferedImage catDyingSpriteSheet;
    private BufferedImage catDeadSpriteSheet;
    private BufferedImage heartSpriteSheet;

    private Image[] catIdleSprite;
    private Image[] catEatSprite;
    private Image[] catDyingSprite;
    private Image[] catDeadSprite;
    private Image[] heartSprite;

    private final int CAT_DISPLAY_IMAGE_WIDTH = 256;
    private final int CAT_DISPLAY_IMAGE_HEIGHT = 256;
    private final int CAT_PIXEL_WIDTH = 32;
    private final int CAT_PIXEL_HEIGHT = 32;
    private final int CAT_IDLE_FRAMES = 4;
    private final int CAT_EAT_FRAMES = 7;
    private final int CAT_DYING_FRAMES = 2;
    private final int CAT_DEAD_FRAMES = 1;

    private boolean facingRight = true;
    private int index = 0;
    Timer timer = new Timer(1000,this);
    int time = 0;

    //this class manages the display of Game elements within the frame. it constructs the buttons/graphics.
    //Action listeners are placed on the buttons so view controller and/or cat controller can be notifed when
    //certain buttons are pressed. The animation of the cat changes on a timer. Every one second the timer will call
    //the action listener and check the CatStateType again. If it remains the same then it moves onto the next frame
    //of the animation, else it resets the frame count back to 0 and play the request animation.
    public Game(ViewController viewController, CatController catController) {
        this.viewController = viewController;
        this.catController = catController;

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
        debugHungryButton = new JButton("Debug Hungry");
        debugHungryButton.setBounds(20,140, 100,20);
        debugHungryButton.addActionListener(this);

        buttonList.add(playButton);
        buttonList.add(feedButton);
        buttonList.add(debugHungryButton);
        buttonList.add(menuButton);
        //draw cat lazy loads the sprite base on CatStateType
        drawCat();
        catLabel = new JLabel();
        //display cat decides what gets shown in the label base on CatStateType
        initCatDisplay(catLabel);
        catLabel.setBounds(150,150,CAT_DISPLAY_IMAGE_WIDTH,CAT_DISPLAY_IMAGE_HEIGHT);

        timer.start();

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.lightGray);
        this.add(menuButton);
        this.add(feedButton);
        this.add(playButton);
        this.add(debugHungryButton);
        this.add(catLabel);
        this.setLayout(null);

        Graphics2D g2D = (Graphics2D) g;
    }

    public void initCatDisplay(JLabel label)
    {
        if(catController.getCat().getCatStateType().equals(CatStateType.IDLE))
        {
            label.setIcon(new ImageIcon(catIdleSprite[0]));
        }
        else if(catController.getCat().getCatStateType().equals(CatStateType.DYING))
        {
            label.setIcon(new ImageIcon(catDyingSprite[0]));
        }
        else if(catController.getCat().getCatStateType().equals(CatStateType.DEAD))
        {
            label.setIcon(new ImageIcon(catDeadSprite[0]));
        }
    }

    //disable buttons during animations to stop users from spam clicking button and potentially cause a bug
    public void disableButtons()
    {
        for(JButton button: buttonList)
        {
            button.setEnabled(false);
        }
    }

    //re-enable buttons after animation end
    public void enableButtons()
    {
        for(JButton button: buttonList)
        {
            button.setEnabled(true);
        }
    }

    //lazy load the cat sprite and save all the animation to catIdleSprite list
    //There are currently 4 Sprite images each being 32 x 32 pixels
    //used a for loop to get the sub images inside the sprite sheet
    //tempImage uses scaleUpImage method and scales up the 32 x 32 pixel art to appear larger on screen

    public void initCatIdle()
    {
        catIdleSprite = new Image[CAT_IDLE_FRAMES];
        if(catIdleSpriteSheet == null)
        {
            try {
                catIdleSpriteSheet = ImageIO.read(getClass().getClassLoader().getResource("CatIdle.png"));
                for(int i = 0; i < CAT_IDLE_FRAMES; i++)
                {
                    Image tempImage = catIdleSpriteSheet.getSubimage(i*32,0,CAT_PIXEL_WIDTH,CAT_PIXEL_HEIGHT);
                    catIdleSprite[i] = scaleUpImage(CAT_DISPLAY_IMAGE_WIDTH, CAT_DISPLAY_IMAGE_HEIGHT, tempImage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //lazy load the cat sprite and save all the animation to catEatSprite

    public void initCatEat()
    {
        catEatSprite = new Image[CAT_EAT_FRAMES];
        if(catEatSpriteSheet == null)
        {
            try {
                catEatSpriteSheet = ImageIO.read(getClass().getClassLoader().getResource("CatEat.png"));
                for(int i = 0; i < CAT_EAT_FRAMES; i++)
                {
                    Image tempImage = catEatSpriteSheet.getSubimage(i*32,0,CAT_PIXEL_WIDTH,CAT_PIXEL_HEIGHT);
                    catEatSprite[i] = scaleUpImage(CAT_DISPLAY_IMAGE_WIDTH, CAT_DISPLAY_IMAGE_HEIGHT, tempImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initDeadCat()
    {
        catDeadSprite = new Image[CAT_DEAD_FRAMES];
        if(catDeadSpriteSheet == null)
        {
            try {
                catDeadSpriteSheet = ImageIO.read(getClass().getClassLoader().getResource("CatDead.png"));
                for(int i = 0; i < CAT_DEAD_FRAMES; i++)
                {
                    Image tempImage = catDeadSpriteSheet.getSubimage(i*32,0,CAT_PIXEL_WIDTH,CAT_PIXEL_HEIGHT);
                    catDeadSprite[i] = scaleUpImage(CAT_DISPLAY_IMAGE_WIDTH, CAT_DISPLAY_IMAGE_HEIGHT, tempImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initDyingCat()
    {
        catDyingSprite = new Image[CAT_DYING_FRAMES];
        if(catDyingSpriteSheet == null)
        {
            try {
                catDyingSpriteSheet = ImageIO.read(getClass().getClassLoader().getResource("CatDying.png"));
                for(int i = 0; i < CAT_DYING_FRAMES; i++)
                {
                    Image tempImage = catDyingSpriteSheet.getSubimage(i*32,0,CAT_PIXEL_WIDTH,CAT_PIXEL_HEIGHT);
                    catDyingSprite[i] = scaleUpImage(CAT_DISPLAY_IMAGE_WIDTH, CAT_DISPLAY_IMAGE_HEIGHT, tempImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void drawCat()
    {
        if(catController.getCat().getCatStateType().equals(CatStateType.IDLE))
        {
            if(catIdleSprite == null)
            {
                initCatIdle();
            }
        }
        else if(catController.getCat().getCatStateType().equals(CatStateType.EATING))
        {
            if(catEatSprite == null)
            {
                initCatEat();
            }
        }
        else if(catController.getCat().getCatStateType().equals(CatStateType.DYING))
        {
            if(catDyingSprite == null)
            {
                initDyingCat();
            }
        }
        else if(catController.getCat().getCatStateType().equals(CatStateType.DEAD))
        {
            if(catDeadSprite == null)
            {
                initDeadCat();
            }
        }
    }

    public Image scaleUpImage(int scaleWidth, int scaleHeight, Image image)
    {
        return image.getScaledInstance(scaleWidth,scaleHeight,Image.SCALE_SMOOTH);

    }

    //Actions performed whenever someone clicks on a button or whenever timer calls it every second
    @Override
    public void actionPerformed(ActionEvent e)
    {


        //TODO REMOVE WHEN DONE
        if(e.getSource() == debugHungryButton)
        {
            catController.hungryCat();
        }
        if(e.getSource() == menuButton)
        {
            viewController.setGameState(GameStateType.MENU);
        }
        if(e.getSource() == feedButton)
        {
            catController.feedCat();
            catController.setCatState(CatStateType.EATING);
            System.out.println(catController.getCat().getFullness());
        }
        if(e.getSource() == timer)
        {
            time++;

            catController.catDecayHunger();
            catController.updateCatState();
            drawCat();

            //this is the logic for displaying cat idle animation
            //Math random to decide when the direct he face changes
            if(catController.getCat().getCatStateType().equals(CatStateType.IDLE))
            {
                if((int)(Math.random()*10)+1 == 1 )
                {
                    if(facingRight == true)
                    {
                        facingRight = false;
                    }
                    else if(facingRight == false)
                    {
                        facingRight = true;
                    }
                }
                if(time % 2 == 0 && facingRight == true)
                {
                    catLabel.setIcon(new ImageIcon(catIdleSprite[0]));
                }
                else if(time % 2 != 0 && facingRight == true)
                {
                    catLabel.setIcon(new ImageIcon(catIdleSprite[1]));
                }
                else if(time % 2 == 0 && facingRight == false)
                {
                    catLabel.setIcon(new ImageIcon(catIdleSprite[2]));
                }
                else if(time % 2 != 0 && facingRight == false)
                {
                    catLabel.setIcon(new ImageIcon(catIdleSprite[3]));
                }
            }
            else if(catController.getCat().getCatStateType().equals(CatStateType.EATING))
            {
                //disable feed button so it can't be spam clicked and re-enable it after the animation ends
                disableButtons();
                catLabel.setIcon(new ImageIcon(catEatSprite[index]));
                index++;
                if(index >= CAT_EAT_FRAMES)
                {
                    index = 0;
                    catController.setCatState(CatStateType.IDLE);
                    enableButtons();
                }
            }
            else if(catController.getCat().getCatStateType().equals(CatStateType.DYING))
            {
                if(time % 2 == 0)
                {
                    catLabel.setIcon(new ImageIcon(catDyingSprite[0]));
                }
                else
                {
                    catLabel.setIcon(new ImageIcon(catDyingSprite[1]));
                }
            }
            else if(catController.getCat().getCatStateType().equals(CatStateType.DEAD))
            {
                catLabel.setIcon(new ImageIcon(catDeadSprite[0]));
                disableButtons();
            }

        }


    }
}
