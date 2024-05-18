package view.animators;

import enums.Sprite;

public abstract class Animator {

    protected int spriteNum, spriteCounter;

    public abstract void increaseSpriteCounter();

    public abstract void increaseSpriteNum();

    public abstract Sprite getDrawSprite();

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void updateSprite() {
        increaseSpriteCounter();
        increaseSpriteNum();
    }
}
