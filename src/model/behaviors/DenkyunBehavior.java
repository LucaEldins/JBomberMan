package model.behaviors;

import controller.TileCollisionChecker;
import enums.Direction;
import model.GameModel;
import model.Tile;
import model.enemies.Enemy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DenkyunBehavior implements Behavior{

    private Enemy enemy;
    private static List<Direction> directionList;

    public DenkyunBehavior(Enemy enemy){
        this.enemy = enemy;
        directionList = new ArrayList<>();
        directionList.add(Direction.UP);
        directionList.add(Direction.DOWN);
        directionList.add(Direction.LEFT);
        directionList.add(Direction.RIGHT);
    }

    @Override
    public void updateDirection() {
        boolean tilesCollision = TileCollisionChecker.checkCollision(enemy, enemy.getDirection(),GameModel.getInstance().getLevel());
        if (!tilesCollision) {
            if (enemy.getDirection() == Direction.UP && !(enemy.getY() - enemy.getSpeed() < 0)) enemy.setY(enemy.getY() - enemy.getSpeed());
            else if (enemy.getDirection() == Direction.DOWN && !(enemy.getY() + enemy.getSpeed() > 480)) enemy.setY(enemy.getY() + enemy.getSpeed());
            else if (enemy.getDirection() == Direction.LEFT && !(enemy.getX() - enemy.getSpeed() < 0)) enemy.setX(enemy.getX() - enemy.getSpeed());
            else if (enemy.getDirection() == Direction.RIGHT && !(enemy.getX() + enemy.getSpeed() > 576)) enemy.setX(enemy.getX() + enemy.getSpeed());
            else {
                pickRandomDirection(enemy);
            }
        }
        else {
            pickRandomDirection(enemy);
        }
    }

    private static void pickRandomDirection(Enemy enemy){
        Random random = new Random();
        ArrayList<Direction> availableDirection = new ArrayList<>();
        directionList.stream().forEach(direction -> {
            if (direction == Direction.UP && (enemy.getY() - enemy.getSpeed() < 0)) return;
            if (direction == Direction.DOWN && (enemy.getY() + enemy.getSpeed() > 480)) return;
            if (direction == Direction.LEFT && (enemy.getX() - enemy.getSpeed() < 0)) return;
            if (direction == Direction.RIGHT && (enemy.getX() + enemy.getSpeed() > 576)) return;
            int x = enemy.getX() / 48;
            int y = enemy.getY() / 48;
            switch (direction){
                case UP -> y-=1;
                case DOWN -> y+=1;
                case LEFT -> x-=1;
                case RIGHT -> x+=1;
            }
            Tile nextTile = GameModel.getInstance().getLevel().getTileMap()[x][y];
            if (nextTile.getCollision()) return;
            availableDirection.add(direction);
        });
        enemy.setDirection(availableDirection.get(random.nextInt(availableDirection.size())));
    }
}
