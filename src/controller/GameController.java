package controller;

import controller.events.NextLevelEvent;
import controller.events.PlayerBombDrop;
import controller.events.PlayerMove;
import enums.Levels;
import model.BomberMan;
import model.GameModel;
import model.Level;
import model.Player;
import view.AudioManager;
import view.DisposeEvent;
import view.GamePanel;
import view.GameView;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class GameController implements Runnable {

    private Thread gameThread;
    private double delta = 0;
    private double drawInterval = 1000000000 / 60;
    private long previousTime = System.nanoTime();
    private long currentTime;
    private boolean hasPlayerLost = false;
    private GameModel gameModel;
    private GameView gameView;
    private PlayerMove playerMove;
    private PlayerBombDrop playerBombDrop;
    private NextLevelEvent nextLevelEvent;
    private PlayerBombController playerBombController;
    private EnemyMovementController enemyMovementController;
    private static Clip activeClip;

    private static GameController instance;

    public static GameController getInstance(){
        if (instance == null) instance = new GameController();
        return instance;
    }

    private GameController() {
        this.gameModel = GameModel.getInstance();
        this.gameView = GameView.getInstance();
        this.playerBombController = new PlayerBombController();
        this.gameModel.register(gameView.getGamePanel());
        this.gameModel.register(gameView.getScoreboardPanel());
        this.playerMove = new PlayerMove();
        this.playerBombDrop = new PlayerBombDrop();
        this.nextLevelEvent = new NextLevelEvent(gameView,gameModel);
        this.gameView.addKeyListener(nextLevelEvent);
        this.gameView.addKeyListener(playerBombDrop);
        this.gameView.addKeyListener(playerMove);
        this.enemyMovementController = new EnemyMovementController();
        activeClip = AudioManager.getInstance().loop("src/song/level1.wav");

    }

    @Override
    public void run() {
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - previousTime) / drawInterval;
            previousTime = currentTime;
            if (delta >= 1) {
                update();
                delta--;
            }
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        playerMove.updatePlayerPosition();
        enemyMovementController.updateEnemyPosition();
        gameModel.checkEntityCollision();
        playerBombController.decreaseBombsTime();
        gameView.getGamePanel().updateSprites();
        BomberMan.getInstance().decreaseDeathTimer();
        gameView.getGamePanel().updateExplosionAnimators();
        gameModel.checkIfPowerUpHasBeenTaken();
        this.hasPlayerLost();
    }

    public static Clip getActiveClip() {
        return activeClip;
    }

    public void hasPlayerLost(){
        if (BomberMan.getInstance().getHealth() == 0){
            if (hasPlayerLost) return;
            hasPlayerLost = true;
            Player.getInstance().addLostGame();
            activeClip.close();
            reset();
            int scelta = JOptionPane.showConfirmDialog(gameView.getGamePanel(),"Hai perso! Vuoi ricominciare un'altra partita?","Seleziona",JOptionPane.YES_NO_OPTION);
            if (scelta == JOptionPane.YES_OPTION) {
                Player.getInstance().addWonGame();
                Player.getInstance().setTotalScore(Player.getInstance().getTotalScore() + BomberMan.getInstance().getScore());
                nextLevelEvent.loadLevel(Levels.LEVEL1);
                BomberMan.getInstance().setHealth(2);
                BomberMan.getInstance().setScore(0);
                Player.getInstance().addGamePlayed();
                BomberMan.getInstance().setBombCount(BomberMan.getInstance().getMaxBombCount());
                getInstance().startGameThread();
                hasPlayerLost = false;
                gameModel.notifyObservers(this);
            } else {
                gameView.dispose();
                hasPlayerLost = false;
            }



        }
    }

    public void reset(){
        gameThread = null;
        instance = null;
    }

}


