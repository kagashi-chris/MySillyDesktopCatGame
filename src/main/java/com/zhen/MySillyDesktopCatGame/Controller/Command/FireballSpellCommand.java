package com.zhen.MySillyDesktopCatGame.Controller.Command;

import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.Model.Rat;

import java.util.Set;

public class FireballSpellCommand implements Command{

    private int fireballDamage = 10;
    private int fireballPixelRange = 300;
    private String commandName = "Fireball";
    private GameState gameState;


    public FireballSpellCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void execute() {
        System.out.println("FIREBALLL!!!!!!!!!!!!!!!!!!!!!!!!");
        Set<Rat> ratSet = gameState.getRatSet();
        for(Rat rat: ratSet)
        {
            if(rat.getX() >= GameWindow.GAME_WINDOW_WIDTH/2 -fireballPixelRange && rat.getX() <= GameWindow.GAME_WINDOW_WIDTH/2 + fireballPixelRange && rat.getY() >= GameWindow.GAME_WINDOW_HEIGHT/2 - fireballPixelRange && rat.getY() <= GameWindow.GAME_WINDOW_HEIGHT/2 + fireballPixelRange)
            {
                if(rat != null)
                {
                    rat.setHp(0);
                }
            }
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
