package controller.events;

import model.Bomb;
import model.BomberMan;
import model.GameModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerBombDrop implements KeyListener {

    private GameModel model = GameModel.getInstance();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E) {
            boolean bombDropped = BomberMan.getInstance().dropBomb();
            if (bombDropped) {
                int x = BomberMan.getInstance().getX();
                int y = BomberMan.getInstance().getY();
                int gridXCoordinate = x / 48;
                int gridYCoordinate = y / 48;
                if (x % 48 > 24) gridXCoordinate++;
                if (y % 48 > 24) gridYCoordinate++;
                Bomb bomb = new Bomb(gridXCoordinate * 48, gridYCoordinate * 48);
                model.getLevel().getBombsDropped().add(bomb);
                model.notifyObservers(bomb);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }


}
