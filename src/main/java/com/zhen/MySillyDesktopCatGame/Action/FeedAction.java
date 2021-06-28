package com.zhen.MySillyDesktopCatGame.Action;

import com.zhen.MySillyDesktopCatGame.Action.Action;
import com.zhen.MySillyDesktopCatGame.Model.Cat;

public class FeedAction implements Action {

    private Cat cat;

    public FeedAction(Cat cat) {
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
