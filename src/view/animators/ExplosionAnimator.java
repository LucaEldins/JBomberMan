package view.animators;

import enums.Direction;
import enums.Sprite;
import model.Tile;
import view.animators.Animator;

public class ExplosionAnimator extends Animator {

    private Tile tile;
    private Direction direction;

    public ExplosionAnimator(Tile tile) {
        this.tile = tile;
    }

    public ExplosionAnimator(Tile tile, Direction direction) {
        this.tile = tile;
        this.direction = direction;
    }


    @Override
    public void increaseSpriteCounter() {
        if (spriteNum == 7 && spriteCounter < 4) {
            spriteCounter++;
            spriteNum = 0;
        }
    }

    @Override
    public void increaseSpriteNum() {
        spriteNum++;
    }

    @Override
    public Sprite getDrawSprite() {
        Sprite sprite = null;
        if (direction == null) {
            switch (spriteCounter) {
                case 0 -> sprite = Sprite.EXPLOSIONCENTER1;
                case 1 -> sprite = Sprite.EXPLOSIONCENTER2;
                case 2 -> sprite = Sprite.EXPLOSIONCENTER3;
                case 3 -> sprite = Sprite.EXPLOSIONCENTER4;
                case 4 -> sprite = Sprite.EXPLOSIONCENTER5;
            }
            return sprite;
        }
        switch (direction){
            case UP -> {
                switch (spriteCounter) {
                    case 0 -> sprite = Sprite.EXPLOSIONUP1;
                    case 1 -> sprite = Sprite.EXPLOSIONUP2;
                    case 2 -> sprite = Sprite.EXPLOSIONUP3;
                    case 3 -> sprite = Sprite.EXPLOSIONUP4;
                    case 4 -> sprite = Sprite.EXPLOSIONUP5;
                }
            }
            case DOWN -> {
                switch (spriteCounter) {
                    case 0 -> sprite = Sprite.EXPLOSIONDOWN1;
                    case 1 -> sprite = Sprite.EXPLOSIONDOWN2;
                    case 2 -> sprite = Sprite.EXPLOSIONDOWN3;
                    case 3 -> sprite = Sprite.EXPLOSIONDOWN4;
                    case 4 -> sprite = Sprite.EXPLOSIONDOWN5;
                }
            }
            case LEFT -> {
                switch (spriteCounter) {
                    case 0 -> sprite = Sprite.EXPLOSIONLEFT1;
                    case 1 -> sprite = Sprite.EXPLOSIONLEFT2;
                    case 2 -> sprite = Sprite.EXPLOSIONLEFT3;
                    case 3 -> sprite = Sprite.EXPLOSIONLEFT4;
                    case 4 -> sprite = Sprite.EXPLOSIONLEFT5;
                }
            }
            case RIGHT -> {
                switch (spriteCounter) {
                    case 0 -> sprite = Sprite.EXPLOSIONRIGHT1;
                    case 1 -> sprite = Sprite.EXPLOSIONRIGHT2;
                    case 2 -> sprite = Sprite.EXPLOSIONRIGHT3;
                    case 3 -> sprite = Sprite.EXPLOSIONRIGHT4;
                    case 4 -> sprite = Sprite.EXPLOSIONRIGHT5;
                }
            }
        }
        return sprite;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
