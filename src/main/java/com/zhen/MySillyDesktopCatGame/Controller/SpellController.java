package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Command.Command;
import com.zhen.MySillyDesktopCatGame.Type.SpellSlotType;

import java.util.Stack;

public class SpellController {

    private Command[] spellCommand = new Command[4];
    Stack<Command> spellCommandHistoryStack = new Stack<>();
    private MainController mainController;

    public SpellController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setSpellCommand(SpellSlotType spellSlotType, Command command){
        spellCommand[spellSlotType.ordinal()] = command;
    }

    public void executeSpellCommand(SpellSlotType spellSlotType)
    {
        spellCommand[spellSlotType.ordinal()].execute();
        spellCommandHistoryStack.push(spellCommand[spellSlotType.ordinal()]);
        spellCommand[spellSlotType.ordinal()] = null;
    }

    public void undoSpellCommand()
    {

    }

    public Command[] getSpellCommand() {
        return spellCommand;
    }


}
