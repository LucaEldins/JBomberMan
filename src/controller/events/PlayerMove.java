package controller.events;

import controller.TileCollisionChecker;
import enums.Direction;
import model.GameModel;
import model.BomberMan;
import view.animators.BomberManAnimator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerMove implements KeyListener {

    private boolean moved = false;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> upPressed = true;
            case KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_D -> rightPressed = true;
        }
        if (upPressed) BomberMan.getInstance().setPlayerDirection(Direction.UP);
        if (downPressed) BomberMan.getInstance().setPlayerDirection(Direction.DOWN);
        if (leftPressed) BomberMan.getInstance().setPlayerDirection(Direction.LEFT);
        if (rightPressed) BomberMan.getInstance().setPlayerDirection(Direction.RIGHT);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
        }
        BomberManAnimator.getInstance().resetSpriteCountNum();
        moved = false;

    }

    public void updatePlayerPosition() {
        BomberMan bomberMan = BomberMan.getInstance();
        if (!bomberMan.isCanMove()) return;
        if (upPressed) bomberMan.setPlayerDirection(Direction.UP);
        else if (downPressed) bomberMan.setPlayerDirection(Direction.DOWN);
        else if (leftPressed) bomberMan.setPlayerDirection(Direction.LEFT);
        else if (rightPressed) bomberMan.setPlayerDirection(Direction.RIGHT);
        if (!(upPressed || downPressed || rightPressed || leftPressed)) return;
        boolean collision = TileCollisionChecker.checkCollision(bomberMan, bomberMan.getPlayerDirection(), GameModel.getInstance().getLevel());
        if (!collision) {
            if (upPressed && !(bomberMan.getY() - bomberMan.getSpeed() < 0)) bomberMan.setY(bomberMan.getY() - bomberMan.getSpeed());
            else if (downPressed && !(bomberMan.getY() + bomberMan.getSpeed() > 480))
                bomberMan.setY(bomberMan.getY() + bomberMan.getSpeed());
            else if (rightPressed && !(bomberMan.getX() + bomberMan.getSpeed() > 576))
                bomberMan.setX(bomberMan.getX() + bomberMan.getSpeed());
            else if (leftPressed && !(bomberMan.getX() - bomberMan.getSpeed() < 0))
                bomberMan.setX(bomberMan.getX() - bomberMan.getSpeed());
            if (upPressed || downPressed || rightPressed || leftPressed) moved = true;
        }
        if (moved) {
            BomberManAnimator.getInstance().increaseSpriteNum();
            BomberManAnimator.getInstance().increaseSpriteCounter();
            GameModel.getInstance().notifyObservers(this);
        }
    }
}
