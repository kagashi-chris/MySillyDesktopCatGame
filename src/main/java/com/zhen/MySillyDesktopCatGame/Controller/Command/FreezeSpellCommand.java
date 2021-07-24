package com.zhen.MySillyDesktopCatGame.Controller.Command;

import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Model.Rat;

public class FreezeSpellCommand implements Command{

    private int freezeDamage = 0;
    private int fireballPixelRange = 400;
    private String commandName = "Freeze";
    private GameState gameState;

    public FreezeSpellCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void execute() {
        System.out.println("FREEZEEEEEEEEEEEE!");
        for(Rat rat: gameState.getRatSet())
        {
            rat.setSpeed(0);
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
