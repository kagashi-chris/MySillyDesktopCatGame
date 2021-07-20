package com.zhen.MySillyDesktopCatGame.Controller.Command;

public interface Command {

    public void execute();
    public void undo();
    public String getCommandName();
}
