package model.powerups;

import model.BomberMan;

public class LifePowerUp extends PowerUp{


    public LifePowerUp(int x, int y) {
        super(x, y);
    }

    @Override
    public void powerUpTaken() {
        BomberMan.getInstance().setDamage(BomberMan.getInstance().getDamage() + 1);
    }
}
