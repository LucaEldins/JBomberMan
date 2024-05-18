package view.animators;

import enums.Sprite;
import model.Bomb;
import view.animators.Animator;

public class BombAnimator extends Animator {

    private Bomb bomb;

    public BombAnimator(Bomb bomb) {
        this.bomb = bomb;
    }

    @Override
    public void increaseSpriteCounter() {
        if (spriteNum > 20) {
            if (spriteCounter < 5) spriteCounter++;
            if (spriteCounter == 5) spriteCounter = 1;
            spriteNum = 0;
        }
    }

    @Override
    public void increaseSpriteNum() {
        spriteNum++;
    }

    @Override
    public Sprite getDrawSprite() {
        return switch (spriteCounter) {
            case 2, 4 -> Sprite.BOMB2;
            case 3 -> Sprite.BOMB3;
            default -> Sprite.BOMB;
        };
    }

    public Bomb getBomb() {
        return bomb;
    }
}
