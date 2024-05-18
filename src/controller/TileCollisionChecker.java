package controller;

import enums.Direction;
import model.*;

import java.awt.*;

public class TileCollisionChecker {


    public static boolean checkCollision(Entity entity, Direction direction, Level level) {
        boolean collision = false;
        int topLeftX = entity.getX();
        int topRightX = entity.getX() + 45;
        int topY = entity.getY();
        int bottomY = entity.getY() + 45;
        int newTopLeftRow;
        int newTopRightCol;
        int newTopLeftCol;
        Tile tile1, tile2;
        switch (direction) {
            case UP -> {
                newTopLeftRow = (topY - entity.getSpeed()) / 48;
                tile1 = level.getTileMap()[topLeftX / 48][newTopLeftRow];
                tile2 = level.getTileMap()[topRightX / 48][newTopLeftRow];
                if ((tile1.getCollision() && tile2.getCollision())) collision = true;
                else if (tile1.getCollision() && !tile2.getCollision()) {
                    collision = true;
                    entity.setX(entity.getX() + (entity.getSpeed()));
                } else if (!tile1.getCollision() && tile2.getCollision()) {
                    collision = true;
                    entity.setX(entity.getX() - entity.getSpeed());
                }

            }
            case DOWN -> {
                newTopLeftRow = (bottomY + entity.getSpeed()) / 48;
                tile1 = level.getTileMap()[topLeftX / 48][newTopLeftRow];
                tile2 = level.getTileMap()[topRightX / 48][newTopLeftRow];
                if ((tile1.getCollision() && tile2.getCollision())) collision = true;
                else if (tile1.getCollision() && !tile2.getCollision()) {
                    collision = true;
                    entity.setX(entity.getX() + entity.getSpeed());
                } else if (!tile1.getCollision() && tile2.getCollision()) {
                    collision = true;
                    entity.setX((entity.getX() - entity.getSpeed()));
                }
            }
            case RIGHT -> {
                newTopRightCol = (topRightX + entity.getSpeed()) / 48;
                tile1 = level.getTileMap()[newTopRightCol][topY / 48];
                tile2 = level.getTileMap()[newTopRightCol][bottomY / 48];
                if (tile1.getCollision() && tile2.getCollision()) collision = true;
                else if (!tile1.getCollision() && tile2.getCollision()) {
                    collision = true;
                    entity.setY(entity.getY() - entity.getSpeed());
                } else if (tile1.getCollision() && !tile2.getCollision()) {
                    collision = true;
                    entity.setY(entity.getY() + entity.getSpeed());
                }

            }
            case LEFT -> {
                newTopLeftCol = (topLeftX - entity.getSpeed()) / 48;
                tile1 = level.getTileMap()[newTopLeftCol][topY / 48];
                tile2 = level.getTileMap()[newTopLeftCol][bottomY / 48];
                if (tile1.getCollision() && tile2.getCollision()) collision = true;
                else if (!tile1.getCollision() && tile2.getCollision()) {
                    collision = true;
                    entity.setY(entity.getY() - entity.getSpeed());
                } else if (tile1.getCollision() && !tile2.getCollision()) {
                    collision = true;
                    entity.setY(entity.getY() + entity.getSpeed());
                }


            }
        }
        if (!collision) {
            for (Bomb bomb : level.getBombsDropped()) {
                Rectangle e1 = new Rectangle(entity.getX(), entity.getY(), 48, 48);
                Rectangle e2 = new Rectangle(bomb.getX(), bomb.getY(), 48, 48);

                if (PlayerBombController.isOverlapping(e1, e2)) return false;
                switch (direction) {
                    case UP -> e1.setBounds(entity.getX(), entity.getY() - entity.getSpeed(), 48, 48);
                    case DOWN -> e1.setBounds(entity.getX(), entity.getY() + entity.getSpeed(), 48, 48);
                    case LEFT -> e1.setBounds(entity.getX() - entity.getSpeed(), entity.getY(), 48, 48);
                    case RIGHT -> e1.setBounds(entity.getX() + entity.getSpeed(), entity.getY(), 48, 48);
                }

                collision = PlayerBombController.isOverlapping(e1, e2);
            }
        }
        return collision;
    }
}

