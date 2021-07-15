package com.zhen.MySillyDesktopCatGame.Command;

import com.zhen.MySillyDesktopCatGame.Model.FireballSpell;

public class SellFireballCommand extends FireballCommand implements Command{

    public SellFireballCommand(FireballSpell fireballSpell) {
        super(fireballSpell);
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
