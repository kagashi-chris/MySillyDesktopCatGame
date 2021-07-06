package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.RatStateType;

public class Rat {

    private int hp;
    private int speed;
    private RatStateType ratStateType;
    private int x,y;


    public Rat(int hp, int speed, int x, int y) {
        this.hp = hp;
        this.speed = speed;
        this.x = x;
        this.y = y;
        ratStateType = RatStateType.RUNNING;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public RatStateType getRatStateType() {
        return ratStateType;
    }

    public void setRatStateType(RatStateType ratStateType) {
        this.ratStateType = ratStateType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
