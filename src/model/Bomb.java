package model;

public class Bomb extends Entity {


    private int timer = 200;
    private boolean exploded = false;
    private static int range = 1;

    public Bomb(int x, int y) {
        super(x, y, 0);
    }

    public static int getRange() {
        return range;
    }

    public static void setRange(int range) {
        Bomb.range = range;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
}


