package view.animators;

import enums.Sprite;
import model.enemies.Denkyun;

public class DenkyunAnimator extends Animator{

    private Denkyun denkyun;

    public DenkyunAnimator(Denkyun denkyun){
        this.denkyun = denkyun;
    }

    @Override
    public void increaseSpriteCounter() {
        if (spriteNum > 8) {
            if (spriteCounter < 10) spriteCounter++;
            if (spriteCounter == 10) spriteCounter = 1;
            spriteNum = 0;
        }
    }

    @Override
    public void increaseSpriteNum() {
        spriteNum++;
    }

    @Override
    public Sprite getDrawSprite() {
        Sprite sprite = Sprite.DENKYUN1;
        switch (spriteCounter){
            case 1 -> sprite = Sprite.DENKYUN1;
            case 2,10 -> sprite = Sprite.DENKYUN2;
            case 3,9 -> sprite = Sprite.DENKYUN3;
            case 4,8 -> sprite = Sprite.DENKYUN4;
            case 5,7 -> sprite = Sprite.DENKYUN5;
            case 6 -> sprite = Sprite.DENKYUN6;
        }
        return sprite;
    }

    public Denkyun getDenkyun(){
        return denkyun;
    }
}
