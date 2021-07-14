package com.zhen.MySillyDesktopCatGame.Action;

import com.zhen.MySillyDesktopCatGame.Type.SpellType;

public class BuyItemAction implements Action{

    private SpellType spellType;

    public BuyItemAction(SpellType spellType) {
        this.spellType = spellType;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public void setSpellType(SpellType spellType) {
        this.spellType = spellType;
    }
}
