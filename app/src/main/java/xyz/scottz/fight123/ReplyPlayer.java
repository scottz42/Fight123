package xyz.scottz.fight123;

/**
 * Created by lei on 2018/3/29.
 */

public class ReplyPlayer {
    int id,x,y, health;

    public ReplyPlayer(int id,int x, int y, int health) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.health = health;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
