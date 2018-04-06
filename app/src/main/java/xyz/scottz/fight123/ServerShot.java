package xyz.scottz.fight123;

import android.util.Log;

/**
 * Created by lei on 2018/3/28.
 */

// TODO: add type
public class ServerShot {
    int fromX , fromY ;
    int toX , toY ;
    int x , y ;
    static double speed = 0.5 ;
    static int shotDuration = 50;    //ms
    double vx , vy ;    // speed in x & y directions
    long startTime ;
    long lastShotTime;
    double damage=3;
    boolean deleted = false ;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setFrom(int x , int y)
    {
        fromX = x ;
        fromY = y ;
    }

    public void setTo(int x , int y)
    {
        toX = x ;
        toY = y ;
    }

    public int getType()
    {
        // TODO:
        return 0 ;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getLastShotTime() {
        return lastShotTime;
    }

    public int getShotDuration()
    {
        return shotDuration;
    }

    public void onTimer()
    {
        if (startTime<0) { // first time
            startTime = System.currentTimeMillis();  // TODO: do we need both starttime and last shot time?
            lastShotTime = startTime ;
            x = fromX ;
            y = fromY ;

            // calcalute vx , vy
            int dx = toX - fromX ;
            int dy = toY - fromY ;
            if (dx==0&&dy==0)
            {
                dy=1;
            }
            double distance = Math.sqrt(dx*dx+dy*dy);
            vx = speed*dx/distance;
            vy = speed*dy/distance;

            updateClients() ;       // new shot
        } else {
            long currentTime=System.currentTimeMillis();
            if (currentTime-lastShotTime>shotDuration) {
                lastShotTime = currentTime;
                updatePosition();
                checkCollision();
                updateClients();       // shot position , player update if there is damage
            }
        }
    }

    public void updateClients()
    {
        for (int i=0 ; i<ServerLogic.getTotalPlayers() ; i++) {
            ServerLogic.getUpdateInfo().get(i).addShot(this);
        }
    }

    public void updatePosition()
    {
        x = (int) (fromX + vx*(System.currentTimeMillis()-startTime));
        y = (int) (fromY + vy*(System.currentTimeMillis()-startTime));
        //Log.d(null,"shot position:"+x+","+y);

        // TODO:
        if (x<0 || y<0 || x>2000 || y>1200) {
            deleted = true;
        }
    }


    public void checkCollision()
    {
        for (ServerPlayer serverPlayer :ServerLogic.getPlayers())
        {
            if (serverPlayer.checkShot(x,y)){
                serverPlayer.doDamage(damage);
                for (int i=0 ; i<ServerLogic.getTotalPlayers();i++) {
                    ServerLogic.getUpdateInfo().get(i).addUpdatePlayer(serverPlayer);
                }
                deleted = true ;
            }
        }
        // check position against player
        updateClients();
    }
}
