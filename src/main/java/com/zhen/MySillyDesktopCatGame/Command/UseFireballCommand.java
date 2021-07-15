package com.zhen.MySillyDesktopCatGame.Command;

import com.zhen.MySillyDesktopCatGame.Model.FireballSpell;

public class UseFireballCommand extends FireballCommand implements Command{

    public UseFireballCommand(FireballSpell fireballSpell) {
        super(fireballSpell);
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
