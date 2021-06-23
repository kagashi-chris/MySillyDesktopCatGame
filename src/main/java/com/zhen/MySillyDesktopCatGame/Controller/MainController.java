package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Type.*;
import com.zhen.MySillyDesktopCatGame.Util.FileUtil;
import com.zhen.MySillyDesktopCatGame.View.MainWindowView;

public class MainController implements Runnable{

    private boolean programRunning = false;
    private Thread thread;

    private MainWindowView mainWindowView;

    private SillyCatGameController sillyCatGameController;
    private MenuController menuController;
    private MinigameController minigameController;
    private AnimationController animationController;

    private GameState gameState;

    public MainController() {
        gameState = new GameState(GameStateType.MENU);
        GameWindow gameWindow = new GameWindow();

        menuController = new MenuController(this);
        sillyCatGameController = new SillyCatGameController(this);
        minigameController = new MinigameController(this);
        animationController = new AnimationController(this);


        mainWindowView = new MainWindowView(this);

        start();
    }

    //gets the csv data and converts it into a cat object
//    private void initGameFile()
//    {
//        cat = FileUtil.csvReader("Cat.csv");
//    }


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
        mainWindowView.mainViewLoop();
    }

    public void performAction(Action action)
    {
        if (action instanceof SwitchScreenToAction)
        {
            handleSwitchScreenTo((SwitchScreenToAction)action);
        }

    }

    private void handleSwitchScreenTo(SwitchScreenToAction action)
    {
        gameState.setGameStateType(action.getGameStateType());
    }

    protected GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameStateType gameStateType)
    {
        gameState.setGameStateType(gameStateType);
        System.out.println("Game state set to: " + gameStateType.toString());
        if(gameStateType.equals(GameStateType.EXIT))
        {
            System.exit(1);
        }
    }

    public MenuController getMenuController() {
        return menuController;
    }


    public MinigameController getMinigameController() {
        return minigameController;
    }

    public SillyCatGameController getSillyCatGameController() {
        return sillyCatGameController;
    }
}
