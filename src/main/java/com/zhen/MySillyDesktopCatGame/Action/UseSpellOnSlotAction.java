package com.zhen.MySillyDesktopCatGame.Action;

import com.zhen.MySillyDesktopCatGame.Type.SpellSlotType;

public class UseSpellOnSlotAction implements Action{

    SpellSlotType spellSlotType;

    public UseSpellOnSlotAction(SpellSlotType spellSlotType) {
        this.spellSlotType = spellSlotType;
    }

    public SpellSlotType getSpellSlotType() {
        return spellSlotType;
    }

    public void setSpellSlotType(SpellSlotType spellSlotType) {
        this.spellSlotType = spellSlotType;
    }
}
