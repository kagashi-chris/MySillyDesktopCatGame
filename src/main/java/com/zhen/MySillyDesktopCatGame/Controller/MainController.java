package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Model.Cat;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.Util.FileUtil;
import com.zhen.MySillyDesktopCatGame.View.Frame;
import com.zhen.MySillyDesktopCatGame.View.Sprite;
import com.zhen.MySillyDesktopCatGame.View.SpriteSheet;

public class MainController implements Runnable{

    private boolean programRunning = false;
    private Thread thread;
    private Frame frame;

    private Cat cat;

    public MainController() {
        GameState gameState = new GameState(GameStateType.MENU);
        GameWindow gameWindow = new GameWindow();
        initGameState();
        CatController catController = new CatController(cat);
        ViewController viewController = new ViewController(gameState,gameWindow);
        frame = new Frame(gameWindow,gameState,viewController,catController);

        start();
    }

    //gets the csv data and converts it into a cat object
    private void initGameState()
    {
        cat = FileUtil.csvReader("Cat.csv");
    }

    private synchronized void start()
    {
        if(programRunning) return;
        programRunning = true;
        thread = new Thread(this);
        thread.start();

    }
    private synchronized void stop()
    {
        if(!programRunning) return;
        programRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0/60.0;
        int frames = 0;
        int ticks = 0;
        while (programRunning)
        {
            long now = System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime = now;
            while(delta>=1)
            {
                tick();
                ticks++;
                delta--;
            }
            frames++;
            if(System.currentTimeMillis()-timer>1000)
            {
                timer+=1000;
                System.out.println(frames + " Frames Per Second " + ticks + " Updates Per Second");
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick()
    {
        frame.repaint();
    }
}
