package controller.events;

import controller.GameController;
import controller.PlayerBombController;
import enums.Levels;
import model.BomberMan;
import model.GameModel;
import model.Level;
import model.Player;
import model.powerups.NextLevelPowerUp;
import model.powerups.PowerUp;
import view.AudioManager;
import view.GamePanel;
import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NextLevelEvent implements KeyListener {

    private GameView gameView;
    private GameModel gameModel;
    private boolean hasPlayerWon = false;

    public NextLevelEvent(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F) checkIfExitGotPressed();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void loadLevel(Levels level) {
        gameModel.setLevel(new Level(level));
        gameView.remove(gameView.getGamePanel());
        gameModel.unregister(gameView.getGamePanel());
        gameView.setGamePanel(new GamePanel(gameModel));
        gameView.add(gameView.getGamePanel(), BorderLayout.CENTER);
        gameModel.register(gameView.getGamePanel());
        gameView.pack();
        gameModel.getLevel().getBombsDropped().clear();
        gameView.getGamePanel().getBombAnimatorList().clear();
        BomberMan.getInstance().setX(0);
        BomberMan.getInstance().setY(0);
        gameModel.getLevel().getEntityList().add(BomberMan.getInstance());
    }

    private void checkIfExitGotPressed() {
        GameModel.getInstance().getLevel().getPowerUpList().stream().forEach(powerUp -> {
            if (!(powerUp instanceof NextLevelPowerUp)) return;
            Rectangle pu = new Rectangle(powerUp.getX() + 10, powerUp.getY() + 10, 28, 28);
            Rectangle player = new Rectangle(BomberMan.getInstance().getX(), BomberMan.getInstance().getY(), 48, 48);
            if (PlayerBombController.isOverlapping(pu, player)) {
                if (!GameModel.getLevelPath().equals(Levels.LEVEL2.getLevelPath())) {
                    BomberMan.getInstance().setBombCount(BomberMan.getInstance().getMaxBombCount());
                    GameModel.setLevelPath(Levels.LEVEL2.getLevelPath());
                    loadLevel(Levels.LEVEL2);
                } else {
                    if (hasPlayerWon) return;
                    hasPlayerWon = true;
                    Player.getInstance().addWonGame();
                    GameController.getInstance().reset();
                    GameController.getActiveClip().close();
                    int scelta = JOptionPane.showConfirmDialog(gameView.getGamePanel(),"Hai vinto! Vuoi ricominciare un'altra partita?","Seleziona",JOptionPane.YES_NO_OPTION);
                    if (scelta == JOptionPane.YES_OPTION) {
                        this.loadLevel(Levels.LEVEL1);
                        Player.getInstance().setTotalScore(Player.getInstance().getTotalScore() + BomberMan.getInstance().getScore());
                        BomberMan.getInstance().setHealth(2);
                        BomberMan.getInstance().setScore(0);
                        BomberMan.getInstance().setBombCount(BomberMan.getInstance().getMaxBombCount());
                        Player.getInstance().addGamePlayed();
                        GameController.getInstance().startGameThread();
                        gameModel.notifyObservers(this);
                        hasPlayerWon = false;
                    } else {
                        gameView.dispose();
                        hasPlayerWon = false;
                    }
                }
            }
        });
    }
}
