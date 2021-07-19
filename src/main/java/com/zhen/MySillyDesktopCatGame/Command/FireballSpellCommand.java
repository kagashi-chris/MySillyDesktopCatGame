package com.zhen.MySillyDesktopCatGame.Command;

public class FireballSpellCommand implements Command{

    private int fireballDamage = 10;
    private int fireballPixelRange = 150;
    private String commandName = "Fireball";

    public FireballSpellCommand() {

    }

    @Override
    public void execute() {
        System.out.println("FIREBALLL!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void undo() {

    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
