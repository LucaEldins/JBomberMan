package view.animators;

import enums.Direction;
import enums.Sprite;
import model.BomberMan;

public class BomberManAnimator extends Animator {

    private int spriteCounter;
    private int spriteNum = 0;
    private static BomberManAnimator instance;

    private BomberManAnimator() {
    }

    public static BomberManAnimator getInstance() {
        if (instance == null) instance = new BomberManAnimator();
        return instance;
    }

    @Override
    public Sprite getDrawSprite() {
        Sprite image = Sprite.BOMBERMANDOWN;
        if (BomberMan.getInstance().getPlayerDirection() == Direction.LEFT) {
            switch (spriteCounter) {
                case 1 -> image = Sprite.BOMBERMANLEFT;
                case 2 -> image = Sprite.BOMBERMANLEFT2;
                case 3 -> image = Sprite.BOMBERMANLEFT3;
                case 4 -> image = Sprite.BOMBERMANLEFT4;
            }
        }
        if (BomberMan.getInstance().getPlayerDirection() == Direction.RIGHT) {
            image = switch (spriteCounter) {
                case 1 -> Sprite.BOMBERMANRIGHT;
                case 2 -> Sprite.BOMBERMANRIGHT2;
                case 3 -> Sprite.BOMBERMANRIGHT3;
                case 4 -> Sprite.BOMBERMANRIGHT4;
                default -> image;
            };
        }
        if (BomberMan.getInstance().getPlayerDirection() == Direction.UP) {
            image = switch (spriteCounter) {
                case 1 -> Sprite.BOMBERMANUP;
                case 2 -> Sprite.BOMBERMANUP2;
                case 3 -> Sprite.BOMBERMANUP3;
                case 4 -> Sprite.BOMBERMANUP4;
                default -> image;
            };
        }
        if (BomberMan.getInstance().getPlayerDirection() == Direction.DOWN) {
            image = switch (spriteCounter) {
                case 1 -> Sprite.BOMBERMANDOWN;
                case 2 -> Sprite.BOMBERMANDOWN2;
                case 3 -> Sprite.BOMBERMANDOWN3;
                case 4 -> Sprite.BOMBERMANDOWN4;
                default -> image;
            };
        }
        return image;
    }

    public void increaseSpriteCounter() {
        if (spriteNum > 8) {
            if (spriteCounter < 5) spriteCounter++;
            if (spriteCounter == 5) spriteCounter = 1;
            spriteNum = 0;
        }
    }

    @Override
    public void increaseSpriteNum() {
        spriteNum++;
    }

    public void resetSpriteCountNum() {
        spriteCounter = 1;
        spriteNum = 0;
    }

    public void reset(){
        instance = null;
    }
}
