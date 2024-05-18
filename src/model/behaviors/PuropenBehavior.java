package model.behaviors;

import controller.TileCollisionChecker;
import enums.Direction;
import model.GameModel;
import model.enemies.Enemy;

public class PuropenBehavior implements Behavior{

    private Enemy enemy;

    public PuropenBehavior(Enemy enemy){
        this.enemy = enemy;
    }


    @Override
    public void updateDirection() {
        boolean tilesCollision = TileCollisionChecker.checkCollision(enemy, enemy.getDirection(), GameModel.getInstance().getLevel());
        if (!tilesCollision) {
            if (enemy.getDirection() == Direction.UP) {
                if (!(enemy.getY() - enemy.getSpeed() < 0)) enemy.setY(enemy.getY() - enemy.getSpeed());
                else enemy.setDirection(Direction.DOWN);
            } else if (enemy.getDirection() == Direction.DOWN) {
                if (!(enemy.getY() + enemy.getSpeed() > 480)) enemy.setY(enemy.getY() + enemy.getSpeed());
                else enemy.setDirection(Direction.UP);
            } else if (enemy.getDirection() == Direction.LEFT) {
                if (!(enemy.getX() - enemy.getSpeed() < 0)) enemy.setX(enemy.getX() - enemy.getSpeed());
                else enemy.setDirection(Direction.RIGHT);
            } else if (enemy.getDirection() == Direction.RIGHT) {
                if (!(enemy.getX() + enemy.getSpeed() > 576)) enemy.setX(enemy.getX() + enemy.getSpeed());
                else enemy.setDirection(Direction.LEFT);
            }
        } else {
            switch (enemy.getDirection()) {
                case UP -> enemy.setDirection(Direction.DOWN);
                case DOWN -> enemy.setDirection(Direction.UP);
                case LEFT -> enemy.setDirection(Direction.RIGHT);
                case RIGHT -> enemy.setDirection(Direction.LEFT);
            }
        }
    }
}
