package xyz.scottz.fight123;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by lei on 2018/3/17.
 */

public class Player {
    int id;
    int team;
    double health ;
    double maxHealth;
    Weapon weapon;
    private int x , y ;
    Game game ;

    public Player(double health, int x, int y) {
        this.health = health;
        this.x = x;
        this.y = y;
        maxHealth=health;
    }

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

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean move(int dx , int dy)
    {
        x += dx ;
        y += dy ;
       return game.serverUpdatePosition(this);
        // game.checkGem(this);
    }

    // player makes one shot: send shot info to server, then update game state based on returned server state
    public boolean shoot()
    {
        game.serverUpdateShots(this);
        return true ;
    }

    public void onDraw (Canvas canvas , Paint paint , int offsetX  , int offsetY)
    {
        //  TODO：boundary clipping
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        Rect r = new Rect(x-offsetX-15 , y-offsetY-15 , x-offsetX+15 , y-offsetY+15);
        canvas.drawRect(r , paint) ;
        paint.setColor(Color.RED);
        double percent=health/maxHealth;
        if (percent<0){
            percent=0;
        }
        r=new Rect(x-offsetX-15,y-offsetY-25,(int)(x-offsetX-15+30*percent),y-offsetY-15);
        canvas.drawRect(r , paint) ;

    }


}
