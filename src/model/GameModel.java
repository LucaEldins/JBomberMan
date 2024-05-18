package model;

import controller.PlayerBombController;
import enums.Direction;
import model.enemies.Enemy;
import model.powerups.NextLevelPowerUp;
import model.powerups.PowerUp;
import observer.Observable;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameModel extends Observable {


    private BomberMan bomberMan;
    private Level level;
    private ArrayList<Tile> explosionTiles;
    private static GameModel instance;
    private static String levelPath, enemyPath,powerUpsPath;

    public void setLevel(Level level){
        this.level = level;
    }

    public static void setEnemyPath(String enemyPath) {
        GameModel.enemyPath = enemyPath;
    }

    public static void setPowerUpsPath(String powerUpsPath) {
        GameModel.powerUpsPath = powerUpsPath;
    }


    public static void setLevelPath(String levelPath){
        GameModel.levelPath = levelPath;
    }

    public static String getLevelPath(){
        return levelPath;
    }

    public static String getEnemyPath(){
        return enemyPath;
    }

    public static String getPowerUpsPath(){
        return powerUpsPath;
    }

    public static GameModel getInstance(){
        if (instance == null) instance = new GameModel();
        return instance;
    }

    private GameModel() {
        this.bomberMan = BomberMan.getInstance();
        bomberMan.setHealth(2);
        bomberMan.setX(0);
        bomberMan.setY(0);
        bomberMan.setSpeed(2);
        bomberMan.setDamage(1);
        level = new Level(levelPath,enemyPath,powerUpsPath);
        level.getEntityList().add(bomberMan);
    }

    public Level getLevel() {
        return level;
    }

    public ArrayList<Tile> getExplosionTiles() {
        return explosionTiles;
    }

    public void checkEntityCollision() {
        ArrayList<Entity> entityList = level.getEntityList();
        for (int i = 0; i < entityList.size(); i++) {
            for (int j = i + 1; j < entityList.size(); j++) {
                Entity e1 = entityList.get(i);
                Entity e2 = entityList.get(j);
                Rectangle r1 = new Rectangle(e1.getX(), e1.getY(), 48, 48);
                Rectangle r2 = new Rectangle(e2.getX(), e2.getY(), 48, 48);
                if (PlayerBombController.isOverlapping(r1, r2)) {
                    if ((e1.equals(bomberMan) || e2.equals(bomberMan)) && (!bomberMan.isDead() && !bomberMan.isInvincible())) {
                        bomberMan.setDead(true);
                        Enemy enemy;
                        if (e1 instanceof Enemy) enemy = (Enemy) e1;
                        else enemy = (Enemy) e2;
                        switch (enemy.getDirection()) {
                            case UP -> enemy.setDirection(Direction.DOWN);
                            case DOWN -> enemy.setDirection(Direction.UP);
                            case LEFT -> enemy.setDirection(Direction.RIGHT);
                            case RIGHT -> enemy.setDirection(Direction.LEFT);
                        }
                    } else if (!(e1.equals(bomberMan) || e2.equals(bomberMan))) {
                        Enemy enemy1 = (Enemy) e1;
                        Enemy enemy2 = (Enemy) e2;
                        switch (enemy1.getDirection()) {
                            case UP -> enemy1.setDirection(Direction.DOWN);
                            case DOWN -> enemy1.setDirection(Direction.UP);
                            case LEFT -> enemy1.setDirection(Direction.RIGHT);
                            case RIGHT -> enemy1.setDirection(Direction.LEFT);
                        }
                        switch (enemy2.getDirection()) {
                            case UP -> enemy2.setDirection(Direction.DOWN);
                            case DOWN -> enemy2.setDirection(Direction.UP);
                            case LEFT -> enemy2.setDirection(Direction.RIGHT);
                            case RIGHT -> enemy2.setDirection(Direction.LEFT);
                        }
                    }
                }
            }
        }
    }

    public void setExplosionTiles(ArrayList<Tile> explosionTiles) {
        this.explosionTiles = explosionTiles;
        this.notifyObservers(this);
    }

    public void checkIfPowerUpHasBeenTaken(){
        Iterator<PowerUp> iterator = level.getPowerUpList().iterator();
        while (iterator.hasNext()){
            PowerUp powerup = iterator.next();
            if (!(powerup instanceof NextLevelPowerUp) && PlayerBombController.isOverlapping(new Rectangle(powerup.getX() + 10,powerup.getY() + 10,38,38),new Rectangle(BomberMan.getInstance().getX(), BomberMan.getInstance().getY(),48,48))){
                powerup.powerUpTaken();
                iterator.remove();
                GameModel.getInstance().notifyObservers(this);
            }
        }
    }

    public void reset(){
        instance = null;
    }

}
