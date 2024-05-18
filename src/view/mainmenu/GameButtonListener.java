package view.mainmenu;

import controller.GameController;
import enums.Levels;
import model.GameModel;
import model.Player;
import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Player.getNickname() == null) {
            JOptionPane.showMessageDialog((Component) e.getSource(),"Nome utente non inserito!");
            return;
        }
        Player.getInstance();
        GameModel.setLevelPath(Levels.LEVEL1.getLevelPath());
        GameModel.setEnemyPath(Levels.LEVEL1.getEnemyPath());
        GameModel.setPowerUpsPath(Levels.LEVEL1.getPowerUpsPath());
        GameModel.getInstance();
        GameView.getInstance();
        GameController controller = GameController.getInstance();
        controller.startGameThread();
        Player.getInstance().addGamePlayed();
    }
}
