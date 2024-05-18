package view;

import controller.GameController;
import enums.Direction;
import enums.Sprite;
import model.*;
import model.enemies.Denkyun;
import model.enemies.Enemy;
import model.enemies.Puropen;
import model.powerups.BombCountPowerUp;
import model.powerups.LifePowerUp;
import model.powerups.NextLevelPowerUp;
import observer.Observer;
import view.animators.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel implements Observer {

    private GameModel model;
    private static final int screenWidth = 624;
    private static final int screenHeight = 528;
    private final List<Animator> animatorList;
    private final List<BombAnimator> bombAnimatorList;
    private final List<ExplosionAnimator> explosionAnimators;


    public List<BombAnimator> getBombAnimatorList(){
        return bombAnimatorList;
    }

    public GamePanel(GameModel gameModel) {
        animatorList = new ArrayList<>();
        bombAnimatorList = new ArrayList<>();
        explosionAnimators = new ArrayList<>();
        this.model = gameModel;
        initializeDestroyableWalls();
        initializeEnemiesAnimators();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.cyan);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }


    public void initializeEnemiesAnimators() {
        model.getLevel().getEntityList().stream().forEach(entity -> {
            if (entity instanceof Puropen puropen) {
                animatorList.add(new PuropenAnimator(puropen));
            }
            if (entity instanceof Denkyun denkyun){
                animatorList.add(new DenkyunAnimator(denkyun));
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.drawLevel(g2);
        this.drawDestroyableWallsSprites(g2);
        this.drawEnemies(g2);
        this.drawBombs(g2);
        this.drawPlayer(g2);
        this.drawExplosion(g2);
    }


    private void initializeDestroyableWalls() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                Tile tile = model.getLevel().getTileMap()[i][j];
                if (tile.isDestroyable()) animatorList.add(new DestroyableWallAnimator(tile));
            }
        }
    }

    private void drawDestroyableWallsSprites(Graphics2D g) {
        animatorList.stream().forEach(animator -> {
            if (animator instanceof DestroyableWallAnimator wallAnimator) {
                Tile tile = wallAnimator.getTile();
                g.drawImage(wallAnimator.getDrawSprite().getImage(), tile.getX(), tile.getY(), 48, 48, null);
            }
        });
    }

    private void drawPlayer(Graphics2D g) {
        g.drawImage(BomberManAnimator.getInstance().getDrawSprite().getImage(), BomberMan.getInstance().getX(), BomberMan.getInstance().getY(), 48, 48, null);
    }

    private void drawEnemies(Graphics2D g) {
        animatorList.stream().forEach(animator -> {
            if (animator instanceof PuropenAnimator puropenAnimator) {
                g.drawImage(animator.getDrawSprite().getImage(), puropenAnimator.getRobottino().getX(), puropenAnimator.getRobottino().getY(), 48, 48, null);
            }
            if (animator instanceof DenkyunAnimator denkyunAnimator){
                g.drawImage(animator.getDrawSprite().getImage(), denkyunAnimator.getDenkyun().getX(),denkyunAnimator.getDenkyun().getY(),48,48,null);
            }
        });
    }

    private void drawLevel(Graphics2D g) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                g.drawImage(Sprite.PEACETOWNGRASS.getImage(), i * 48, j * 48, 48, 48, null);
            }
        }
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                Tile tile = model.getLevel().getTileMap()[i][j];
                if (tile.isDestroyable()) continue;
                g.drawImage(tile.getTileSprite().getImage(), tile.getX(), tile.getY(), 48, 48, null);
            }
        }

        model.getLevel().getPowerUpList().stream().forEach(powerUp -> {
            BufferedImage image = null;
            if (powerUp instanceof BombCountPowerUp) image = Sprite.BOMBPOWERUP.getImage();
            else if (powerUp instanceof LifePowerUp) image = Sprite.LIFEPOWERUP.getImage();
            else if (powerUp instanceof NextLevelPowerUp) image = Sprite.EXITPOWERUP.getImage();
            g.drawImage(image,powerUp.getX(),powerUp.getY(),48,48,null);
        });

        animatorList.stream().forEach(animator -> {
            if (animator instanceof DestroyableWallAnimator wallAnimator) {
                Tile tile = wallAnimator.getTile();
                g.drawImage(animator.getDrawSprite().getImage(), tile.getX(), tile.getY(), 48, 48, null);
            }
        });

    }

    @Override
    public void update(Object o) {
        if (o instanceof Bomb) {
            bombAnimatorList.add(new BombAnimator((Bomb) o));
        }
        if (o instanceof Tile tile) {
            animatorList.removeIf(animator -> animator instanceof DestroyableWallAnimator && ((DestroyableWallAnimator) animator).getTile().equals(tile));
        }
        if (o instanceof Enemy) {
            animatorList.removeIf(animator -> animator instanceof PuropenAnimator && ((PuropenAnimator) animator).getRobottino().equals(o));
            animatorList.removeIf(animator -> animator instanceof DenkyunAnimator && ((DenkyunAnimator) animator).getDenkyun().equals(o));
        }
        if (o instanceof GameController){

        }
        this.repaint();

    }

    private void drawBombs(Graphics2D g) {
        Iterator<BombAnimator> iterator = bombAnimatorList.iterator();
        while (iterator.hasNext()) {
            BombAnimator bombAnimator = iterator.next();
            if (bombAnimator.getBomb().isExploded()) {
                iterator.remove();
                continue;
            }
            g.drawImage(bombAnimator.getDrawSprite().getImage(), bombAnimator.getBomb().getX(), bombAnimator.getBomb().getY(), 48, 48, null);
        }
    }

    public void updateExplosionAnimators() {
        if (model.getExplosionTiles() == null || model.getExplosionTiles().isEmpty()) return;
        for (int i = 0; i < (4 * Bomb.getRange()) + 1; i++) {
            Tile explosionTile = model.getExplosionTiles().get(i);
            if (explosionTile == null) continue;
            ExplosionAnimator animator = new ExplosionAnimator(explosionTile);
            switch (i) {
                case 1 -> animator.setDirection(Direction.UP);
                case 2 -> animator.setDirection(Direction.DOWN);
                case 3 -> animator.setDirection(Direction.RIGHT);
                case 4 -> animator.setDirection(Direction.LEFT);
                default -> animator.setDirection(null);
            }
            explosionAnimators.add(animator);
        }
        model.getExplosionTiles().clear();
    }

    private void drawExplosion(Graphics2D g) {
        Iterator<ExplosionAnimator> iterator = explosionAnimators.iterator();
        while (iterator.hasNext()) {
            ExplosionAnimator animator = iterator.next();
            g.drawImage(animator.getDrawSprite().getImage(), animator.getTile().getX(), animator.getTile().getY(), 48, 48, null);
            if (animator.getSpriteCounter() == 4) iterator.remove();
        }

    }
    public void updateSprites() {
        bombAnimatorList.stream().forEach(Animator::updateSprite);
        explosionAnimators.stream().forEach(Animator::updateSprite);
        animatorList.stream().forEach(Animator::updateSprite);
        this.repaint();
    }

}
