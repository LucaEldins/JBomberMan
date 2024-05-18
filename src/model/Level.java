package model;

import enums.Direction;
import enums.Levels;
import enums.Sprite;
import model.enemies.Denkyun;
import model.enemies.Enemy;
import model.enemies.Puropen;
import model.powerups.LifePowerUp;
import model.powerups.NextLevelPowerUp;
import model.powerups.PowerUp;
import model.powerups.BombCountPowerUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Level {

    private Tile[][] tileMap = new Tile[13][11];
    private ArrayList<Bomb> bombsDropped = new ArrayList<Bomb>();
    private ArrayList<Entity> entityList = new ArrayList<Entity>();
    private ArrayList<PowerUp> powerUpList = new ArrayList<PowerUp>();
    private HashMap<Integer, Direction> movementHashMap = new HashMap<Integer, Direction>();


    public Level(Levels level) {
        movementHashMap.put(1, Direction.UP);
        movementHashMap.put(2, Direction.DOWN);
        movementHashMap.put(3, Direction.LEFT);
        movementHashMap.put(4, Direction.RIGHT);
        loadFileMap(level.getLevelPath());
        loadEnemies(level.getEnemyPath());
        loadPowerUps(level.getPowerUpsPath());
    }

    public Level(String levelPath, String enemyPath,String powerUpsPath){
        movementHashMap.put(1, Direction.UP);
        movementHashMap.put(2, Direction.DOWN);
        movementHashMap.put(3, Direction.LEFT);
        movementHashMap.put(4, Direction.RIGHT);
        loadFileMap(levelPath);
        loadEnemies(enemyPath);
        loadPowerUps(powerUpsPath);
    }

    public void loadPowerUps(String path){
        Iterator<String> iterator;
        try {
            iterator = Files.lines(Path.of(path)).iterator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (iterator.hasNext()) {
            PowerUp powerUp = null;
            String[] line = iterator.next().split((" "));
            switch (line[0]) {
                case "1":
                    powerUp = new BombCountPowerUp(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                    break;
                case "2":
                    powerUp = new LifePowerUp(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                    break;
                case "3":
                    powerUp = new NextLevelPowerUp(Integer.parseInt(line[1]),Integer.parseInt(line[2]));

            }
            powerUpList.add(powerUp);
        }
    }

    public void loadEnemies(String path) {
        Iterator<String> iterator;
        try {
            iterator = Files.lines(Path.of(path)).iterator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (iterator.hasNext()) {
            Enemy enemy = null;
            String[] line = iterator.next().split((" "));
            switch (line[0]) {
                case "1":
                    enemy = new Puropen(Integer.parseInt(line[1]), Integer.parseInt(line[2]), 2, movementHashMap.get(Integer.parseInt(line[3])));
                    break;
                case "2":
                    enemy = new Denkyun(Integer.parseInt(line[1]), Integer.parseInt(line[2]), 2, movementHashMap.get(Integer.parseInt(line[3])));
                    break;

            }
            entityList.add(enemy);
        }
    }


    public void loadFileMap(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            for (int j = 0; j < 11; j++) {
                String[] line = scanner.nextLine().split(" ");
                for (int i = 0; i < 13; i++) {
                    int value = Integer.parseInt(line[i]);
                    switch (value) {
                        case 1 -> tileMap[i][j] = new Tile(Sprite.PEACETOWNGRASS, false, 48 * i, 48 * j, false);
                        case 2 -> tileMap[i][j] = new Tile(Sprite.PEACETOWNWALLDESTROYABLE1, true, 48 * i, 48 * j, true);
                        case 3 -> tileMap[i][j] = new Tile(Sprite.PEACETOWNWALL, true, 48 * i, 48 * j, false);
                    }
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {

        }
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }


    public ArrayList<Bomb> getBombsDropped() {
        return bombsDropped;
    }


    public ArrayList<Entity> getEntityList() {
        return entityList;
    }


    public void setEntityList(ArrayList<Entity> enemyList) {
        this.entityList = enemyList;
    }

    public ArrayList<PowerUp> getPowerUpList() {
        return powerUpList;
    }
}
