package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Util.SpriteUtil;
import com.zhen.MySillyDesktopCatGame.View.AnimatedSprite;

import java.awt.Image;

public class AnimationController{

    private MainController mainController;

    private AnimatedSprite idleAnimation;
    private Image eatingSpriteSheet;
    private Image dyingSpriteSheet;
    private Image deadSpriteSheet;
    private SpriteUtil spriteUtil = new SpriteUtil();
    private int index = 0;

    public AnimationController(MainController mainController) {
        this.mainController = mainController;

//        cat.setCatCurrentImage(getIdleSpriteSheetInstance().getCurrentFrame());
//        mainController.updateImage();
//        State.idle = new Idle();
//        State.eating = new Eating();
//        State.dying = new Dying();
//        State.dead = new Dead();

//        getCurrentState();
//        updateCatImage();
    }

    public void animationNext()
    {
        index++;
        System.out.println(index);
    }



    public AnimatedSprite getIdleSpriteSheetInstance()
    {
        if(idleAnimation == null)
        {
//            idleAnimation = spriteUtil.getImagesFromSpriteSheet("CatIdle.png",4,32,32,256,256);
        }
        return idleAnimation;
    }


//
//    public void updateState()
//    {
//        getCurrentState();
//        State.current.enter();
//        State.current.update(cat);
//    }

//    private void getCurrentState(){
//        Cat cat = mainController.getGameState().getCat();
//        switch (cat.getCatStateType())
//        {
//            case IDLE:
//                State.current = State.idle;
//                break;
//
//            case EATING:
//                State.current = State.eating;
//                break;
//
//            case DYING:
//                State.current = State.dying;
//                break;
//
//            case DEAD:
//                State.current = State.dead;
//                break;
//
//            default:
//                break;
//        }
//    }
//
//    private void updateCatImage()
//    {
//
//    }
//}
//
//abstract class State{
//
//    static State idle, eating, dying, dead, current;
//
//    void enter(String path){}
//    void update(Cat cat){}
//}
//
//class Idle extends State{
//
//    private Image idleSpriteSheet;
//
//    void enter(String path)
//    {
//        System.out.println("Currently in idle state");
//    }
//
//    void update(Cat cat)
//    {
//        cat.setCatStateType(CatStateType.IDLE);
//    }
//}
//
//class Eating extends State{
//
//    void enter(String path)
//    {
//        System.out.println("Currently in eating state");
//    }
//
//    void update(Cat cat)
//    {
//        cat.setCatStateType(CatStateType.EATING);
//    }
//}
//
//class Dying extends State{
//
//    void enter(String path)
//    {
//        System.out.println("Currently in dying state");
//    }
//
//    void update(Cat cat)
//    {
//        cat.setCatStateType(CatStateType.DYING);
//    }
//}
//
//class Dead extends State{
//
//    void enter(String path)
//    {
//        System.out.println("Currently dead state");
//    }
//
//    void update(Cat cat)
//    {
//        cat.setCatStateType(CatStateType.DEAD);
//    }
}


