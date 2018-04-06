package xyz.scottz.fight123;

/**
 * Created by lei on 2018/3/28.
 */

public class ServerPlayer {
    int id , team ;
    int x , y , health;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
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

    // true if hit
    public boolean checkShot(int shotX , int shotY)
    {
        // TODO: real team choice
        return (team==0 && shotX>x-40 && shotX<x+40 && shotY>y-40 && shotY<y+40);
    }

    public void doDamage(double damage)
    {
        health -= damage ;
    }
}
