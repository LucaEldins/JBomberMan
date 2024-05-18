package view.animators;

import enums.Direction;
import enums.Sprite;
import model.enemies.Puropen;
import view.animators.Animator;

public class PuropenAnimator extends Animator {

    private final Puropen robottino;


    public Puropen getRobottino() {
        return robottino;
    }

    public PuropenAnimator(Puropen puropen) {
        this.robottino = puropen;
    }

    @Override
    public void increaseSpriteCounter() {
        if (spriteNum > 10) {
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
        Sprite image = Sprite.ROBOTTINOLEFT1;
        if (robottino.getDirection() == Direction.LEFT) {
            image = switch (spriteCounter) {
                case 1 -> Sprite.ROBOTTINOLEFT1;
                case 2 -> Sprite.ROBOTTINOLEFT2;
                case 3 -> Sprite.ROBOTTINOLEFT3;
                case 4 -> Sprite.ROBOTTINOLEFT4;
                default -> image;
            };
        }
        if (robottino.getDirection() == Direction.RIGHT) {
            image = switch (spriteCounter) {
                case 1 -> Sprite.ROBOTTINORIGHT1;
                case 2 -> Sprite.ROBOTTINORIGHT2;
                case 3 -> Sprite.ROBOTTINORIGHT3;
                case 4 -> Sprite.ROBOTTINORIGHT4;
                default -> image;
            };
        }
        if (robottino.getDirection() == Direction.UP) {
            image = switch (spriteCounter) {
                case 1 -> Sprite.ROBOTTINOUP1;
                case 2 -> Sprite.ROBOTTINOUP2;
                case 3 -> Sprite.ROBOTTINOUP3;
                case 4 -> Sprite.ROBOTTINOUP4;
                default -> image;
            };
        }
        if (robottino.getDirection() == Direction.DOWN) {
            image = switch (spriteCounter) {
                case 1 -> Sprite.ROBOTTINODOWN1;
                case 2 -> Sprite.ROBOTTINODOWN2;
                case 3 -> Sprite.ROBOTTINODOWN3;
                case 4 -> Sprite.ROBOTTINODOWN4;
                default -> image;
            };
        }
        return image;
    }
}
