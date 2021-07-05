package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Action.Action;
import com.zhen.MySillyDesktopCatGame.Action.FeedAction;
import com.zhen.MySillyDesktopCatGame.Action.MakeHungryDebugAction;
import com.zhen.MySillyDesktopCatGame.Action.SwitchScreenToAction;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;
import com.zhen.MySillyDesktopCatGame.View.MainWindowView;
import com.zhen.MySillyDesktopCatGame.View.View;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainController implements Runnable{

    private boolean programRunning = false;
    private Thread thread;

    private MainWindowView mainWindowView;

    private SillyCatGameController sillyCatGameController;
    private MenuController menuController;
    private MinigameController minigameController;

    private List<View> viewObservers = new CopyOnWriteArrayList<>();

    private GameState gameState;

    public MainController() {
        gameState = new GameState(GameStateType.MENU);

        mainWindowView = new MainWindowView(this);
        menuController = new MenuController(this);
        sillyCatGameController = new SillyCatGameController(this);
        minigameController = new MinigameController(this);
        start();
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

                ticks++;
                delta--;
            }
            frames++;
            if(System.currentTimeMillis()-timer>1000)
            {
                tick();
                timer+=1000;
//                System.out.println(frames + " Frames Per Second " + ticks + " Updates Per Second");
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick()
    {
        sillyCatGameController.tick();
        mainWindowView.tick();
        notifyObservers(gameState);
    }

    public synchronized void performAction(Action action)
    {
        System.out.println("Perform action called" );
        if (action instanceof SwitchScreenToAction)
        {
            handleSwitchScreenTo((SwitchScreenToAction)action);
        }
        else if (action instanceof FeedAction)
        {
            sillyCatGameController.handleFeedCat((FeedAction) action);
        }
        else if (action instanceof MakeHungryDebugAction)
        {
            sillyCatGameController.handleMakeHungryDebugAction((MakeHungryDebugAction) action);
        }
        notifyObservers(gameState);
    }

    private synchronized void notifyObservers(GameState gameState)
    {
        for(View view: viewObservers)
        {
            view.updateView(gameState);
        }
    }

    public synchronized void subscribe(View view)
    {
        viewObservers.add(view);
        view.updateView(gameState);
    }

    public synchronized void unsubscribe(View view)
    {
        viewObservers.remove(view);
    }

    private void handleSwitchScreenTo(SwitchScreenToAction action)
    {
        gameState.setGameStateType(action.getGameStateType());
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
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
