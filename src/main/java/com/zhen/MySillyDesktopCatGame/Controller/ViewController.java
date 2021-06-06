package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Model.GameWindow;
import com.zhen.MySillyDesktopCatGame.View.Window;

public class ViewController {

    private Window window;

    public ViewController() {
        GameWindow gameWindow = new GameWindow();
        window = new Window(gameWindow);
        window.createFrame(window);
    }

    public void run()
    {
        window.render(window);
    }
}
