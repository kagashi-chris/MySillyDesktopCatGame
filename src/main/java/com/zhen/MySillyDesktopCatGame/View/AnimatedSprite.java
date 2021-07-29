package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Model.Animal;
import com.zhen.MySillyDesktopCatGame.Type.AnimalStateType;

import javax.swing.Icon;
import javax.swing.JLabel;
import java.util.Map;

public class AnimatedSprite extends JLabel {

    private int currentAnimationFrame = 0;
    private AnimalStateType lastDrawnAnimation = null;
    private Map<AnimalStateType, Icon[]> animationTable;

    public AnimatedSprite(Map<AnimalStateType, Icon[]> animationTable) {
        this.animationTable = animationTable;
    }

    public void draw(Animal animal) {
        this.setBounds(animal.getX(), animal.getY(), 160,160);
        if(lastDrawnAnimation == animal.getAnimalStateType())
        {
            incrementAndWrap(animationTable.get(animal.getAnimalStateType()));
        }
        else
        {
            currentAnimationFrame = 0;
        }
        this.setIcon(animationTable.get(animal.getAnimalStateType())[currentAnimationFrame]);
        lastDrawnAnimation = animal.getAnimalStateType();
    }

    public void incrementAndWrap(Icon[] icons)
    {
        currentAnimationFrame++;
        if(currentAnimationFrame > icons.length - 1)
        {
            currentAnimationFrame = 0;
        }
    }

    public JLabel getjLabel() {
        return this;
    }
}
