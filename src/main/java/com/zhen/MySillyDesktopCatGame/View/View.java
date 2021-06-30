package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Model.GameState;

public interface View {

    void tick();
    void updateView(GameState gameState);

}
