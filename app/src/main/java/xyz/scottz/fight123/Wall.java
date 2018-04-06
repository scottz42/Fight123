package xyz.scottz.fight123;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by lei on 2018/3/18.
 */

public class Wall {
    int x,y,w,h;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void onDraw (Canvas canvas , Paint paint , int offsetX  , int offsetY)
    {
        //  TODO：boundary clipping
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.FILL);
        Rect r = new Rect(x-offsetX , y-offsetY , x-offsetX+w , y-offsetY+h);
        canvas.drawRect(r , paint) ;
    }
}
