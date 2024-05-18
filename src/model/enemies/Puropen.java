package model.enemies;

import enums.Direction;
import model.behaviors.PuropenBehavior;

public class Puropen extends Enemy {
    public Puropen(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction);
        super.setMovementBehavior(new PuropenBehavior(this));
    }
}
