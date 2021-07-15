package com.zhen.MySillyDesktopCatGame.Action;

import com.zhen.MySillyDesktopCatGame.Type.SpellType;

public class UseSpellAction implements Action{

    private SpellType spellType;

    public UseSpellAction(SpellType spellType) {
        this.spellType = spellType;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public void setSpellType(SpellType spellType) {
        this.spellType = spellType;
    }
}
