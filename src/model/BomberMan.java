package model;

import enums.Direction;

public class BomberMan extends Entity {

    private int deathTimer = 500;
    private boolean invincible = false;
    private boolean canMove = true;
    private boolean dead = false;

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    private int damage;
    private int maxBombCount = 1;
    private int bombCount = 1;
    private Direction direction = Direction.DOWN;
    private static BomberMan instance;
    private int score;

    private BomberMan() {
        super(0, 0, 0);
    }

    public int getBombCount(){
        return bombCount;
    }

    public static BomberMan getInstance() {
        if (instance == null) instance = new BomberMan();
        return instance;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void addBomb() {
        bombCount++;
    }

    public boolean dropBomb() {
        if (bombCount > 0) {
            bombCount--;
            return true;
        }
        return false;
    }

    public Direction getPlayerDirection() {
        return direction;
    }

    public void setPlayerDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void decreaseDeathTimer() {
        if ((!dead) && !invincible) {
            return;
        }
        if (dead) canMove = false;
        deathTimer -= 1;
        if (deathTimer ==  350) {
            setHealth(getHealth() - 1);
            GameModel.getInstance().notifyObservers(this);
            setX(0);
            setY(0);
            canMove = true;
            dead = false;
            invincible = true;
        }
        if (deathTimer == 0){
            deathTimer = 500;
            invincible = false;
        }
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public boolean isInvincible(){
        return invincible;
    }

    public void reset(){
        instance = null;
    }

    public int getMaxBombCount() {
        return maxBombCount;
    }

    public void setMaxBombCount(int maxBombCount) {
        this.maxBombCount = maxBombCount;
    }
}
