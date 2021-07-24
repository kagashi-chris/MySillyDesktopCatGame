package com.zhen.MySillyDesktopCatGame.Controller.Command;

import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.Rat;

public class LightningSpellCommand implements Command{
    private int freezeDamage = 0;
    private int lightningSize = 0;
    private String commandName = "Lightning";
    private GameState gameState;

    public LightningSpellCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void execute() {
        for(Rat rat: gameState.getRatSet())
        {
            int randInt = (int)(Math.random()*2)+1;
            if(randInt == 1)
            {
                rat.setHp(0);
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
