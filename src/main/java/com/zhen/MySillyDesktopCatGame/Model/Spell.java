package com.zhen.MySillyDesktopCatGame.Model;

public abstract class Spell {

    private int damage = 0;
    private int cost = 0;
    private int spellRadius = 0;

    public Spell(int damage, int cost, int spellRadius) {
        this.damage = damage;
        this.cost = cost;
        this.spellRadius = spellRadius;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSpellRadius() {
        return spellRadius;
    }

    public void setSpellRadius(int spellRadius) {
        this.spellRadius = spellRadius;
    }
}
