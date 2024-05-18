package enums;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Sprite {

    EXPLOSIONCENTER1(loadImage("src/sprites/exp1/expCenter.png")),
    EXPLOSIONCENTER2(loadImage("src/sprites/exp2/expCenter.png")),
    EXPLOSIONCENTER3(loadImage("src/sprites/exp3/expCenter.png")),
    EXPLOSIONCENTER4(loadImage("src/sprites/exp4/expCenter.png")),
    EXPLOSIONCENTER5(loadImage("src/sprites/exp5/expCenter.png")),
    EXPLOSIONDOWN1(loadImage("src/sprites/exp1/expEndBtm.png")),
    EXPLOSIONDOWN2(loadImage("src/sprites/exp2/expEndBtm.png")),
    EXPLOSIONDOWN3(loadImage("src/sprites/exp3/expEndBtm.png")),
    EXPLOSIONDOWN4(loadImage("src/sprites/exp4/expEndBtm.png")),
    EXPLOSIONDOWN5(loadImage("src/sprites/exp5/expEndBtm.png")),
    EXPLOSIONLEFT1(loadImage("src/sprites/exp1/expEndSx.png")),
    EXPLOSIONLEFT2(loadImage("src/sprites/exp2/expEndSx.png")),
    EXPLOSIONLEFT3(loadImage("src/sprites/exp3/expEndSx.png")),
    EXPLOSIONLEFT4(loadImage("src/sprites/exp4/expEndSx.png")),
    EXPLOSIONLEFT5(loadImage("src/sprites/exp5/expEndSx.png")),
    EXPLOSIONRIGHT1(loadImage("src/sprites/exp1/expEndDx.png")),
    EXPLOSIONRIGHT2(loadImage("src/sprites/exp2/expEndDx.png")),
    EXPLOSIONRIGHT3(loadImage("src/sprites/exp3/expEndDx.png")),
    EXPLOSIONRIGHT4(loadImage("src/sprites/exp4/expEndDx.png")),
    EXPLOSIONRIGHT5(loadImage("src/sprites/exp5/expEndDx.png")),
    EXPLOSIONUP1(loadImage("src/sprites/exp1/expEndUp.png")),
    EXPLOSIONUP2(loadImage("src/sprites/exp2/expEndUp.png")),
    EXPLOSIONUP3(loadImage("src/sprites/exp3/expEndUp.png")),
    EXPLOSIONUP4(loadImage("src/sprites/exp4/expEndUp.png")),
    EXPLOSIONUP5(loadImage("src/sprites/exp5/expEndUp.png")),
    ROBOTTINOLEFT1(loadImage("src/sprites/peacetown/robottinoleft1.png")),
    ROBOTTINOLEFT2(loadImage("src/sprites/peacetown/robottinoleft2.png")),
    ROBOTTINOLEFT3(loadImage("src/sprites/peacetown/robottinoleft3.png")),
    ROBOTTINOLEFT4(loadImage("src/sprites/peacetown/robottinoleft4.png")),
    ROBOTTINORIGHT1(loadImage("src/sprites/peacetown/robottinoright1.png")),
    ROBOTTINORIGHT2(loadImage("src/sprites/peacetown/robottinoright2.png")),
    ROBOTTINORIGHT3(loadImage("src/sprites/peacetown/robottinoright3.png")),
    ROBOTTINORIGHT4(loadImage("src/sprites/peacetown/robottinoright4.png")),
    ROBOTTINODOWN1(loadImage("src/sprites/peacetown/robottinodown1.png")),
    ROBOTTINODOWN2(loadImage("src/sprites/peacetown/robottinodown2.png")),
    ROBOTTINODOWN3(loadImage("src/sprites/peacetown/robottinodown3.png")),
    ROBOTTINODOWN4(loadImage("src/sprites/peacetown/robottinodown4.png")),
    ROBOTTINOUP1(loadImage("src/sprites/peacetown/robottinoup1.png")),
    ROBOTTINOUP2(loadImage("src/sprites/peacetown/robottinoup2.png")),
    ROBOTTINOUP3(loadImage("src/sprites/peacetown/robottinoup3.png")),
    ROBOTTINOUP4(loadImage("src/sprites/peacetown/robottinoup4.png")),
    MAINMENUBG(loadImage("src/sprites/mainmenubg.png")),
    MAINMENU(loadImage("src/sprites/156399.png")),
    BOMBERMANUP(loadImage("src/sprites/player/bpup.png")),
    BOMBERMANUP2(loadImage("src/sprites/player/bpup2.png")),
    BOMBERMANUP3(loadImage("src/sprites/player/bpup3.png")),
    BOMBERMANUP4(loadImage("src/sprites/player/bpup4.png")),
    BOMBERMANLEFT(loadImage("src/sprites/player/bpleft.png")),
    BOMBERMANLEFT2(loadImage("src/sprites/player/bpleft2.png")),
    BOMBERMANLEFT3(loadImage("src/sprites/player/bpleft3.png")),
    BOMBERMANLEFT4(loadImage("src/sprites/player/bpleft4.png")),
    BOMBERMANDOWN(loadImage("src/sprites/player/bpdown.png")),
    BOMBERMANDOWN2(loadImage("src/sprites/player/bpdown2.png")),
    BOMBERMANDOWN3(loadImage("src/sprites/player/bpdown3.png")),
    BOMBERMANDOWN4(loadImage("src/sprites/player/bpdown4.png")),
    BOMBERMANRIGHT(loadImage("src/sprites/player/bpright.png")),
    BOMBERMANRIGHT2(loadImage("src/sprites/player/bpright2.png")),
    BOMBERMANRIGHT3(loadImage("src/sprites/player/bpright3.png")),
    BOMBERMANRIGHT4(loadImage("src/sprites/player/bpright4.png")),
    PEACETOWNGRASS(loadImage("src/sprites/peacetown/grass.png")),
    PEACETOWNGRASSTOP(loadImage("src/sprites/peacetown/grasstop.png")),
    PEACETOWNWALL(loadImage("src/sprites/peacetown/wallpeacetown.png")),
    BOMB(loadImage("src/sprites/bomb.png")),
    BOMB2(loadImage("src/sprites/bomb2.png")),
    BOMB3(loadImage("src/sprites/bomb3.png")),
    PEACETOWNWALLDESTROYABLE1(loadImage("src/sprites/peacetown/destroyablewallpeacetown1.png")),
    PEACETOWNWALLDESTROYABLE2(loadImage("src/sprites/peacetown/destroyablewallpeacetown2.png")),
    PEACETOWNWALLDESTROYABLE3(loadImage("src/sprites/peacetown/destroyablewallpeacetown3.png")),
    PEACETOWNWALLDESTROYABLE4(loadImage("src/sprites/peacetown/destroyablewallpeacetown4.png")),
    SCOREBOARD(loadImage("src/sprites/scoreboard.png")),
    ZERO(loadImage("src/sprites/numbers/0.png")),
    ONE(loadImage("src/sprites/numbers/1.png")),
    TWO(loadImage("src/sprites/numbers/2.png")),
    THREE(loadImage("src/sprites/numbers/3.png")),
    FOUR(loadImage("src/sprites/numbers/4.png")),
    FIVE(loadImage("src/sprites/numbers/5.png")),
    SIX(loadImage("src/sprites/numbers/6.png")),
    SEVEN(loadImage("src/sprites/numbers/7.png")),
    EIGHT(loadImage("src/sprites/numbers/8.png")),
    NINE(loadImage("src/sprites/numbers/9.png")),
    DENKYUN1(loadImage("src/sprites/denkyun1.png")),
    DENKYUN2(loadImage("src/sprites/denkyun2.png")),
    DENKYUN3(loadImage("src/sprites/denkyun3.png")),
    DENKYUN4(loadImage("src/sprites/denkyun4.png")),
    DENKYUN5(loadImage("src/sprites/denkyun5.png")),
    DENKYUN6(loadImage("src/sprites/denkyun6.png")),
    BOMBPOWERUP(loadImage("src/sprites/powerups/bomb.png")),
    LIFEPOWERUP(loadImage("src/sprites/powerups/life.png")),
    EXITPOWERUP(loadImage("src/sprites/powerups/exit.png"));




    private final BufferedImage image;

    Sprite(BufferedImage image) {
        this.image = image;
    }

    private static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {

        }

        return image;

    }

    public BufferedImage getImage() {
        return image;
    }
}
