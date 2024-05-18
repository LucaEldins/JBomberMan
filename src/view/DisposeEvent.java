package view;

import controller.GameController;
import model.BomberMan;
import model.GameModel;
import model.Player;
import view.animators.BomberManAnimator;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DisposeEvent implements WindowListener {

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        Player.getInstance().setTotalScore(Player.getInstance().getTotalScore() + BomberMan.getInstance().getScore());
        reset();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public static void saveUserData(){
        Player player = Player.getInstance();
        File file = new File("src/playerdata/" + Player.getNickname().toLowerCase() + ".txt");
        try {
            String imagePath;
            FileWriter fileWriter = new FileWriter(file, false);
            if (Player.getImagePath() == null) imagePath = "null";
            else imagePath = Player.getImagePath();
            fileWriter.write(player.getGamesPlayed() + " " + player.getGamesWon() + " " + player.getGamesLost() + " " + imagePath + " " + player.getTotalScore());
            fileWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void reset(){
        GameController.getInstance().reset();
        GameController.getActiveClip().close();
        BomberMan.getInstance().reset();
        BomberManAnimator.getInstance().reset();
        GameModel.getInstance().reset();
        GameView.getInstance().reset();
        saveUserData();
    }
}
