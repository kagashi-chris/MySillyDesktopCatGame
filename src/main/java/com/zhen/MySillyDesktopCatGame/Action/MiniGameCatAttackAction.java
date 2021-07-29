package com.zhen.MySillyDesktopCatGame.Action;

import com.zhen.MySillyDesktopCatGame.Model.MinigameCat;

public class MiniGameCatAttackAction implements Action{

    private MinigameCat minigameCat;

    public MiniGameCatAttackAction(MinigameCat minigameCat) {
        this.minigameCat = minigameCat;
    }

    public MinigameCat getMinigameCat() {
        return minigameCat;
    }

    public void setMinigameCat(MinigameCat minigameCat) {
        this.minigameCat = minigameCat;
    }
}
