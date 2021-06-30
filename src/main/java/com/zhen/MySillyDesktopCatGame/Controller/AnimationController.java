package com.zhen.MySillyDesktopCatGame.Controller;

import com.zhen.MySillyDesktopCatGame.Type.CatStateType;
import com.zhen.MySillyDesktopCatGame.Util.SpriteUtil;
import com.zhen.MySillyDesktopCatGame.View.AnimatedSprite;

import java.awt.Image;

public class AnimationController {

    private MainController mainController;

    private AnimatedSprite idleAnimation;
    private Image eatingSpriteSheet;
    private Image dyingSpriteSheet;
    private Image deadSpriteSheet;
    private SpriteUtil spriteUtil = new SpriteUtil();
    private int index = 0;
    private State catState;

    public AnimationController(MainController mainController) {
        this.mainController = mainController;
    }

    public void animationNext() {
        index++;
        System.out.println(index);
    }

    public AnimatedSprite getIdleSpriteSheetInstance() {
        if (idleAnimation == null) {
//            idleAnimation = spriteUtil.getImagesFromSpriteSheet("CatIdle.png",4,32,32,256,256);
        }
        return idleAnimation;
    }

    public void changeState(AnimationController animationController)
    {

    }

    public void idleLeft()
    {
        this.catState.setStateToIdleLeft();
    }

    public void idleRight()
    {
        this.catState.setStateToIdleRight();
    }

    public void eat()
    {
        this.catState.setStateToEating();
    }

    public void dying()
    {
        this.catState.setStateToDying();
    }

    public void dead()
    {
        this.catState.setStateToDead();
    }



    interface State {

        void setStateToIdleLeft();

        void setStateToIdleRight();

        void setStateToEating();

        void setStateToDying();

        void setStateToDead();
    }

    class Idle implements State {
        private AnimationController animationController;

        public Idle(AnimationController animationController) {
            this.animationController = animationController;
        }

        @Override
        public void setStateToIdleLeft() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.IDLE_LEFT);
        }

        @Override
        public void setStateToIdleRight() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.IDLE_RIGHT);
        }

        @Override
        public void setStateToEating() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.EATING);
        }

        @Override
        public void setStateToDying() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.DYING);
        }

        @Override
        public void setStateToDead() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.DEAD);
        }
    }

    class Eating implements State {
        private AnimationController animationController;

        public Eating(AnimationController animationController) {
            this.animationController = animationController;
        }

        @Override
        public void setStateToIdleLeft() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.IDLE_LEFT);
        }

        @Override
        public void setStateToIdleRight() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.IDLE_RIGHT);
        }

        @Override
        public void setStateToEating() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.EATING);
        }

        @Override
        public void setStateToDying() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.DYING);
        }

        @Override
        public void setStateToDead() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.DEAD);
        }
    }

    class Dying implements State {
        private AnimationController animationController;

        public Dying(AnimationController animationController) {
            this.animationController = animationController;
        }

        @Override
        public void setStateToIdleLeft() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.IDLE_LEFT);
        }

        @Override
        public void setStateToIdleRight() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.IDLE_RIGHT);
        }

        @Override
        public void setStateToEating() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.EATING);
        }

        @Override
        public void setStateToDying() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.DYING);
        }

        @Override
        public void setStateToDead() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.DEAD);
        }
    }

    class Dead implements State {
        private AnimationController animationController;

        public Dead(AnimationController animationController) {
            this.animationController = animationController;
        }

        @Override
        public void setStateToIdleLeft() {
            System.out.println("Cat is dead");
        }

        @Override
        public void setStateToIdleRight() {
            System.out.println("Cat is dead");
        }

        @Override
        public void setStateToEating() {
            System.out.println("Cat is dead");
        }

        @Override
        public void setStateToDying() {
            System.out.println("Cat is dead");
        }

        @Override
        public void setStateToDead() {
            animationController.mainController.getGameState().getCatList().get(0).setCatStateType(CatStateType.DEAD);
        }
    }
}


