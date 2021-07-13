package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Action.DamageRatAction;
import com.zhen.MySillyDesktopCatGame.Factory.NormalRatFactory;
import com.zhen.MySillyDesktopCatGame.Factory.RatFactory;
import com.zhen.MySillyDesktopCatGame.Factory.TankyRatFactory;
import com.zhen.MySillyDesktopCatGame.Model.Rat;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import java.util.ArrayList;
import java.util.List;

public class MinigameController {

    private MainController mainController;
    private RatFactory normalRatFactory;
    private RatFactory tankyRatFactory;
    private int ratId = 0;
    private List<Rat> ratsToRemoveList = new ArrayList<>();

    public MinigameController(MainController mainController) {
        this.mainController = mainController;
        normalRatFactory = new NormalRatFactory();
        tankyRatFactory = new TankyRatFactory();

    }

    public void randomlyCreateRat()
    {
        if((int)(Math.random()*20)+1 == 1)
        {
            switch((int)Math.random()*2)
            {
                case 0:
                    mainController.getGameState().getRatSet().add(normalRatFactory.createRat());
                    break;
                case 1:
                    mainController.getGameState().getRatSet().add(tankyRatFactory.createRat());
                    break;
                default:
                    break;
            }

        }
    }

    private void animateRat()
    {
        for(Rat rat: mainController.getGameState().getRatSet())
        {
            rat.setX(rat.getX() - rat.getSpeed());
        }
    }

    private void updateRat()
    {
        for(Rat rat: mainController.getGameState().getRatSet())
        {
            if(rat.getHp() <= 0)
            {
                ratsToRemoveList.add(rat);
            }
        }
        for(Rat rat: ratsToRemoveList)
        {
            mainController.getGameState().getRatSet().remove(rat);
            scorePoint();
        }
        ratsToRemoveList.clear();
    }

    private void scorePoint()
    {
        mainController.getGameState().setCurrentPoints(mainController.getGameState().getCurrentPoints() + 1);
    }

    public void tick()
    {
        if(mainController.getGameState().getGameStateType() == GameStateType.MINIGAME_1)
        {
            updateRat();
            randomlyCreateRat();
            animateRat();
        }
    }

    public void handleDamageRat(DamageRatAction damageRatAction)
    {
        damageRatAction.getRat().setHp(damageRatAction.getRat().getHp()-1);
    }
}
