package controller;

import model.enemies.Enemy;
import model.Entity;
import model.GameModel;
import model.Level;

public class EnemyMovementController {

    private GameModel model;

    public EnemyMovementController() {
        this.model = GameModel.getInstance();
    }

    public void updateEnemyPosition(){
        model.getLevel().getEntityList().stream().forEach(entity -> {
            if (!(entity instanceof Enemy enemy)) return;
            enemy.getMovementBehavior().updateDirection();
        });
    }
}
