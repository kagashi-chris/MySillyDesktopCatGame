package com.zhen.MySillyDesktopCatGame.Command;

public interface Command {

    public void execute();
    public void undo();
    public String getCommandName();
}
