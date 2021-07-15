package com.zhen.MySillyDesktopCatGame.Command;

import com.zhen.MySillyDesktopCatGame.Model.FireballSpell;

public abstract class FireballCommand implements Command{

    private FireballSpell fireballSpell;

    public FireballCommand(FireballSpell fireballSpell) {
        this.fireballSpell = fireballSpell;
    }

    @Override
    public abstract void execute();

    @Override
    public abstract void undo();
}
