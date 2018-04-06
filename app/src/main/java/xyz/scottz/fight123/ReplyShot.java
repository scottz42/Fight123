package xyz.scottz.fight123;

/**
 * Created by lei on 2018/3/29.
 */

public class ReplyShot {
    int type , x , y ;
    long t;
    int duration ;

    public ReplyShot(int type, int x, int y, long t, int duration) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.t = t;
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
