package xyz.scottz.fight123;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by lei on 2018/3/25.
 */

public class ShotInfo {
    int type;
    double x,y,t;
    double duration ;   // duration of shot

    public ShotInfo(int type, double x, double y, double t, double duration) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.t = t;
        this.duration = duration;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void onDraw (Canvas canvas , Paint paint , int offsetX  , int offsetY)
    {
        //  TODO：boundary clipping

        paint.setColor(Color.BLUE);
        //  Log.d(null,"shot position:"+x+","+y);
        paint.setStyle(Paint.Style.FILL);
        Rect r = new Rect((int)x-offsetX-10 ,(int) y-offsetY-5 , (int)x-offsetX+10 , (int)y-offsetY+5);
        canvas.drawRect(r , paint) ;
    }

}
