package model.powerups;

import model.Bomb;
import model.BomberMan;

public class BombCountPowerUp extends PowerUp{


    public BombCountPowerUp(int x, int y) {
        super(x, y);
    }

    @Override
    public void powerUpTaken() {
        BomberMan.getInstance().addBomb();
        BomberMan.getInstance().setMaxBombCount(BomberMan.getInstance().getMaxBombCount() + 1);
    }
}
