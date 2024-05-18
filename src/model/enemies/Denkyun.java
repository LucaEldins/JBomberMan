package model.enemies;

import enums.Direction;
import model.behaviors.DenkyunBehavior;
import model.behaviors.PuropenBehavior;

public class Denkyun extends Enemy {

    public Denkyun(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction);
        super.setMovementBehavior(new DenkyunBehavior(this));
    }
}
