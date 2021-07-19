package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Action.Action;
import com.zhen.MySillyDesktopCatGame.Action.BuyItemAction;
import com.zhen.MySillyDesktopCatGame.Action.DamageRatAction;
import com.zhen.MySillyDesktopCatGame.Action.FeedAction;
import com.zhen.MySillyDesktopCatGame.Action.MakeHungryDebugAction;
import com.zhen.MySillyDesktopCatGame.Action.SwitchScreenToAction;
import com.zhen.MySillyDesktopCatGame.Action.UseSpellAction;
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
    private MiniGameShopController miniGameShopController;
    private SpellController spellController;
    double interpolation = 0;
    final int TICKS_PER_SECOND = 25;
    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    final int MAX_FRAMESKIP = 5;

    private List<View> viewObservers = new CopyOnWriteArrayList<>();

    private GameState gameState;

    public MainController() {
        gameState = new GameState(GameStateType.MENU);

        mainWindowView = new MainWindowView(this);
        menuController = new MenuController(this);
        sillyCatGameController = new SillyCatGameController(this);
        minigameController = new MinigameController(this);
        miniGameShopController = new MiniGameShopController(this);
        spellController = new SpellController(this);
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
        double next_game_tick = System.currentTimeMillis();
        int loops;

        while (true) {
            loops = 0;
            while (System.currentTimeMillis() > next_game_tick
                    && loops < MAX_FRAMESKIP) {

                tick();

                next_game_tick += SKIP_TICKS;
                loops++;
            }

            interpolation = (System.currentTimeMillis() + SKIP_TICKS - next_game_tick
                    / (double) SKIP_TICKS);
//            display_game(interpolation);
        }
    }

    public void tick()
    {
        sillyCatGameController.tick();
        minigameController.tick();
        mainWindowView.tick();
        notifyObservers(gameState);
    }

    public synchronized void performAction(Action action)
    {
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
        else if (action instanceof DamageRatAction)
        {
            minigameController.handleDamageRat((DamageRatAction)action);
        }
        else if (action instanceof BuyItemAction)
        {
            System.out.println("Performing Action BuyItem");
            miniGameShopController.buyItem((BuyItemAction) action);
        }
        else if (action instanceof UseSpellAction)
        {
            minigameController.handleSpellUse((UseSpellAction) action);
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

    public SpellController getSpellController() {
        return spellController;
    }
}
