package com.zhen.MySillyDesktopCatGame.Model;

import com.zhen.MySillyDesktopCatGame.Type.RatStateType;
import com.zhen.MySillyDesktopCatGame.Type.RatType;

public class Rat extends Animal implements Enemy {

    private int hp;
    private int speed;
    private RatType ratType;
    private int id;
    private boolean dead;

    public Rat(int x, int y, int hp, int speed, RatType ratType, int id) {
        super(RatStateType.RUNNING, x, y);
        this.hp = hp;
        this.speed = speed;
        this.ratType = ratType;
        if(ratType == RatType.TANKY)
        {
            hp+=5;
        }
        this.id = id;
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

    public boolean isDead() {
        return dead;
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
                "x=" + x +
                ", y=" + y +
                ", animalStateType=" + animalStateType +
                ", hp=" + hp +
                ", speed=" + speed +
                ", ratType=" + ratType +
                ", id=" + id +
                ", dead=" + dead +
                '}';
    }
}
