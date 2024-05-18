package controller;


import enums.Sprite;
import model.*;
import model.enemies.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayerBombController {

    private final GameModel model;
    private ArrayList<Tile> explosionTiles;
    private ArrayList<Tile> tilesToCheck;

    public PlayerBombController() {
        this.model = GameModel.getInstance();
    }


    public void decreaseBombsTime() {
        ArrayList<Bomb> toBlow = new ArrayList<Bomb>();
        for (Bomb bomb : model.getLevel().getBombsDropped()) {
            int timeLeft = bomb.getTimer();
            if (timeLeft != 0) {
                bomb.setTimer(bomb.getTimer() - 1);
            } else
                toBlow.add(bomb);
        }
        if (!toBlow.isEmpty()) {
            for (Bomb bomb : toBlow)
                blowBomb(bomb);
            model.getLevel().getBombsDropped().removeAll(toBlow);
            model.notifyObservers(toBlow);
            BomberMan.getInstance().addBomb();
            model.notifyObservers(this);
        }

    }

    public void blowBomb(Bomb bomb) {
        bomb.setExploded(true);
        int bombXOnGrid = bomb.getX() / 48;
        int bombYOnGrid = bomb.getY() / 48;
        explosionTiles = new ArrayList<>();
        tilesToCheck = new ArrayList<>();
        for (int i = 0; i < (4 * Bomb.getRange()) + 1; i++) {
            explosionTiles.add(null);
        }
        explosionTiles.set(0, model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid]);
        for (int i = 0; i < Bomb.getRange(); i++) {
            if (!(bombYOnGrid - (i + 1) < 0) && (!model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)].getCollision() || model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)].isDestroyable())) {
                if (!model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)].isDestroyable())
                    tilesToCheck.add(model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)]);
                explosionTiles.set(i * 4 + 1, model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)]);
            }
            if (bombYOnGrid + (i + 1) < 10 && (!model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)].getCollision() || model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)].isDestroyable())) {
                if (!model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)].isDestroyable())
                    tilesToCheck.add(model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)]);
                explosionTiles.set(i * 4 + 2, model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)]);
            }
            if (bombXOnGrid + (i + 1) < 13 && (!model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid].getCollision() || model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid].isDestroyable())) {
                if (!model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid].isDestroyable())
                    tilesToCheck.add(model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid]);
                explosionTiles.set(i * 4 + 3, model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid]);
            }
            if (!(bombXOnGrid - i - 1 < 0) && (!model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid].getCollision() || model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid].isDestroyable())) {
                if (!model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid].isDestroyable())
                    tilesToCheck.add(model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid]);
                explosionTiles.set(i * 4 + 4, model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid]);
            }
            model.setExplosionTiles(explosionTiles);
            if (!(bombYOnGrid - (i + 1) < 0) && model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)].isDestroyable()) {
                model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)].setDestroyable(false);
                model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)].setCollision(false);
                model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)].setSprite(Sprite.PEACETOWNGRASS);
                BomberMan.getInstance().setScore(BomberMan.getInstance().getScore() + 20);
                model.notifyObservers(model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid - (i + 1)]);
            }
            if (bombYOnGrid + (i + 1) < 10 && model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)].isDestroyable()) {
                model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)].setDestroyable(false);
                model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)].setCollision(false);
                model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)].setSprite(Sprite.PEACETOWNGRASS);
                BomberMan.getInstance().setScore(BomberMan.getInstance().getScore() + 20);
                model.notifyObservers(model.getLevel().getTileMap()[bombXOnGrid][bombYOnGrid + (i + 1)]);
            }
            if (bombXOnGrid + (i + 1) < 13 && model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid].isDestroyable()) {
                model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid].setDestroyable(false);
                model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid].setCollision(false);
                model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid].setSprite(Sprite.PEACETOWNGRASS);
                BomberMan.getInstance().setScore(BomberMan.getInstance().getScore() + 20);
                model.notifyObservers(model.getLevel().getTileMap()[bombXOnGrid + (i + 1)][bombYOnGrid]);
            }
            if (!(bombXOnGrid - i - 1 < 0) && model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid].isDestroyable()) {
                model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid].setDestroyable(false);
                model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid].setCollision(false);
                model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid].setSprite(Sprite.PEACETOWNGRASS);
                BomberMan.getInstance().setScore(BomberMan.getInstance().getScore() + 20);
                model.notifyObservers(model.getLevel().getTileMap()[bombXOnGrid - i - 1][bombYOnGrid]);
            }
        }
        checkIfEntityGotHit(model.getLevel().getEntityList().iterator());
    }

    public static boolean isOverlapping(Rectangle r1, Rectangle r2) {
        Point r1BottomRightPoint = new Point((int) r1.getX() + 48, (int) r1.getY() + 48);
        Point r2BottomRightPoint = new Point((int) r2.getX() + 48, (int) r2.getY() + 48);
        return (int) r1.getX() < r2BottomRightPoint.getX() && r1BottomRightPoint.getX() > (int) r2.getX() && (int) r1.getY() < r2BottomRightPoint.getY() && r1BottomRightPoint.getY() > (int) r2.getY();
    }

    public ArrayList<Tile> getExplosionTiles(){
        return explosionTiles;
    }

    public void checkIfEntityGotHit(Iterator<Entity> iterator){
        if (tilesToCheck == null) return;
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            Rectangle entityRectangle = new Rectangle(entity.getX() + 5, entity.getY() + 5, 30, 33);
            tilesToCheck.stream().forEach(tile -> {
                if (tile == null) return;
                Rectangle rectangle = new Rectangle(tile.getX() + 5, tile.getY() + 5, 38, 38);
                boolean damaged = isOverlapping(entityRectangle, rectangle);
                if (damaged) {
                    if (entity instanceof Enemy) {
                        iterator.remove();
                        BomberMan.getInstance().setScore(BomberMan.getInstance().getScore() + 20);
                        model.notifyObservers(entity);
                    }
                    if (entity instanceof BomberMan && (!BomberMan.getInstance().isDead() && !BomberMan.getInstance().isInvincible())) {
                        BomberMan.getInstance().setDead(true);
                    }
                }
            });
        }
    }

}
