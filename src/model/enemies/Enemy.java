package model.enemies;

import enums.Direction;
import model.Entity;
import model.behaviors.Behavior;

public class Enemy extends Entity {

    private Direction direction;
    private Behavior movementBehavior;

    public Enemy(int x, int y, int speed, Direction direction) {
        super(x, y, speed);
        this.direction = direction;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Behavior getMovementBehavior() {
        return movementBehavior;
    }

    public void setMovementBehavior(Behavior movementBehavior){
        this.movementBehavior = movementBehavior;
    }
}
