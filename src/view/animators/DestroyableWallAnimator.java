package view.animators;

import enums.Sprite;
import model.Tile;
import view.animators.Animator;

public class DestroyableWallAnimator extends Animator {

    private Tile tile;

    public DestroyableWallAnimator(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void increaseSpriteCounter() {
        if (spriteNum > 10) {
            if (spriteCounter < 4) spriteCounter++;
            if (spriteCounter == 4) spriteCounter = 1;
            spriteNum = 1;
        }
    }

    @Override
    public void increaseSpriteNum() {
        spriteNum++;
    }

    @Override
    public Sprite getDrawSprite() {
        return switch (spriteCounter) {
            case 2 -> Sprite.PEACETOWNWALLDESTROYABLE2;
            case 3 -> Sprite.PEACETOWNWALLDESTROYABLE3;
            case 4 -> Sprite.PEACETOWNWALLDESTROYABLE4;
            default -> Sprite.PEACETOWNWALLDESTROYABLE1;
        };
    }

    public Tile getTile() {
        return tile;
    }
}

