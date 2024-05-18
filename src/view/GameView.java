package view;

import model.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    private GamePanel gamePanel;
    private ScoreboardPanel scoreboard;
    private static GameView instance;


    public static GameView getInstance(){
        if (instance == null) instance = new GameView();
        return instance;
    }
    private GameView() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.gamePanel = new GamePanel(GameModel.getInstance());
        this.add(gamePanel, BorderLayout.CENTER);
        this.scoreboard = new ScoreboardPanel();
        this.add(scoreboard,BorderLayout.NORTH);
        this.pack();
        this.addWindowListener(new DisposeEvent());
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public ScoreboardPanel getScoreboardPanel(){ return scoreboard;}

    public void reset(){
        instance = null;
    }

}
