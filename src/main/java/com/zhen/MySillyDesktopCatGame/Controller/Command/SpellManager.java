package com.zhen.MySillyDesktopCatGame.Controller.Command;

import com.zhen.MySillyDesktopCatGame.Type.SpellSlotType;

public class SpellManager {

    private Command[] spellCommand = new Command[4];

    public SpellManager() {
    }

    public void setSpellCommand(SpellSlotType spellSlotType, Command command){
        spellCommand[spellSlotType.ordinal()] = command;
    }

    public void executeSpellCommand(SpellSlotType spellSlotType)
    {
        if(spellCommand[spellSlotType.ordinal()] == null)
        {
            return;
        }
        spellCommand[spellSlotType.ordinal()].execute();
        spellCommand[spellSlotType.ordinal()] = null;
    }

    public Command[] getSpellCommand() {
        return spellCommand;
    }


}
