package xyz.scottz.fight123;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by lei on 2018/3/25.
 */

public class FireButton {

    public static boolean onTouch(int x,int y)
    {
        if (x > 1500 && x < 1650 && y > 600 && y < 750) {
            Game.shoot();
            return true;
        }
        return false;
    }

    public static void onDraw (Canvas canvas , Paint paint)
    {
        //  TODO：boundary clipping
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        Rect r = new Rect(1500 , 600 , 1650 , 750);
        canvas.drawRect(r , paint) ;
    }
}
