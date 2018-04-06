package xyz.scottz.fight123;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by lei on 2018/3/18.
 */

public class Bush {
    int x,y,w,h;

    public Bush(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean inBush(int playerX, int playerY)
    {
        return (playerX>x)&&(playerX<x+w)&&(playerY>y)&&(playerY<y+h);
    }

    public void onDraw (Canvas canvas , Paint paint , int offsetX  , int offsetY)
    {
        //  TODO：boundary clipping
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        Rect r = new Rect(x-offsetX , y-offsetY , x-offsetX+w , y-offsetY+h);
        canvas.drawRect(r , paint) ;
    }


}
