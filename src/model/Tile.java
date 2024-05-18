package model;

import enums.Sprite;

public class Tile {

    private Sprite tile;
    private int x, y;
    private boolean collision;
    private boolean destroyable;

    public Tile(Sprite tile, boolean collision, int x, int y, boolean destroyable) {
        this.collision = collision;
        this.tile = tile;
        this.x = x;
        this.y = y;
        this.destroyable = destroyable;
    }

    public Sprite getTileSprite() {
        return tile;
    }

    public boolean getCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSprite(Sprite tile) {
        this.tile = tile;
    }


    public boolean isDestroyable() {
        return destroyable;
    }

    public void setDestroyable(boolean destroyable) {
        this.destroyable = destroyable;
    }
}
