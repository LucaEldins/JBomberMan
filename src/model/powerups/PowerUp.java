package model.powerups;

public abstract class PowerUp {

    private boolean canTake = false;
    private int x,y;

    public PowerUp(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract void powerUpTaken();

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setCanTake(boolean canTake) {
        this.canTake = canTake;
    }
}
