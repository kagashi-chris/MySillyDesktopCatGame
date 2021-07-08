package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.RatStateType;
import com.zhen.MySillyDesktopCatGame.Type.RatType;

public abstract class Rat {

    private int hp;
    private int speed;
    private RatStateType ratStateType;
    private RatType ratType;
    private int x,y;
    private int id;


    public Rat(RatType ratType, int hp, int speed, int x, int y, int id) {
        this.ratType = ratType;
        this.hp = hp;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.id = id;
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

    public RatType getRatType() {
        return ratType;
    }

    public void setRatType(RatType ratType) {
        this.ratType = ratType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rat{" +
                "hp=" + hp +
                ", speed=" + speed +
                ", ratStateType=" + ratStateType +
                ", ratType=" + ratType +
                ", x=" + x +
                ", y=" + y +
                ", id=" + id +
                '}';
    }
}
