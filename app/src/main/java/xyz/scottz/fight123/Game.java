package xyz.scottz.fight123;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by lei on 2018/3/17.
 */

//  TODO: fix damage bug
    // TODO: wall blocking players
    // TODO: main page

public class Game {
    static int noPlayer;
    static double elapsedTime;  // TODO: needed?
    static long startTime = 0;  // TODO: adjust
    // players
    static ArrayList<Player> players;
    static Player me;
    static ArrayList<ShotInfo> shots;
    // map: walls &  bushes
    static ArrayList<Wall> walls;
    static ArrayList<Bush> bushes;

    static long startMoveTime , totalTime ;
    static int startX , startY , endX , endY ;

    public static void init()
    {
        players = new ArrayList<Player>();
        walls = null;
        bushes = null;
        shots = new ArrayList<ShotInfo>();
        startMoveTime=0;
        updateGame(serverAddPlayer());

        if (players.size()>0) {
            me = players.get(0);
        }

        Drone.serverAddDrone();
    }


    public static boolean onTouch(MotionEvent event)
    {
        int  x= (int) event.getX();
        int  y= (int) event.getY();
        if (FireButton.onTouch(x,y))return true;

        // player moving
        startMovePlayer(event);

        return false ;
    }

    public static void startMovePlayer(MotionEvent event)
    {
        startMoveTime = System.currentTimeMillis();
        startX = me.getX();
        startY = me.getY();
        endX = (int) event.getX() ;
        endY = (int) event.getY();
        totalTime = (long) (Math.sqrt((endX-startX)*(endX-startX)+(endY-startY)*(endY-startY))/50*1000) ;
    }

    public static void shoot(){
        me.shoot();
    }

    public static boolean onTimer()
    {
        // send request to server and update
        updateGame(serverGetUpdates());

        // player moving
        movePlayer();

        ServerLogic.onTimer();

        Drone.onTimer();
        return true;
}

    public static void movePlayer()
    {
        if (startMoveTime>0)
        {
            long currentTime = System.currentTimeMillis();
            long dt = currentTime - startMoveTime ;

            if (dt>=totalTime) {
                startMoveTime = 0 ;
            } else {
                int newX = (int) ((endX - startX) * dt / totalTime + startX);
                int newY = (int) ((endY - startY) * dt / totalTime + startY);
                int dX = newX - me.getX();
                int dY = newY - me.getY();
                if (dX != 0 || dY != 0) {
                    me.move(dX, dY);
                }
            }
        }
    }


    public static boolean onDraw(Canvas canvas , Paint paint)
    {
        // draw background
        // TODO: calc offset
        /*
        int offsetX = me.getX();
        int offsetY = me.getY();
        */
        int offsetX = 0 ;
        int offsetY = 0 ;

        drawWalls(canvas , paint , offsetX,offsetY);
        drawBushes(canvas , paint , offsetX,offsetY);
        drawPlayers(canvas , paint , offsetX,offsetY);
        drawShots(canvas , paint , offsetX,offsetY);
        FireButton.onDraw(canvas,paint);
        return true ;

    }
    public static void  drawWalls(Canvas canvas , Paint paint , int offsetX , int offsetY)
    {
        if (walls!=null) {
            for (Wall wall : walls) {
                wall.onDraw(canvas, paint, offsetX, offsetY);
            }
        }
    }

    public static void  drawBushes(Canvas canvas , Paint paint , int offsetX , int offsetY)
    {
        if (bushes!=null) {
            for (Bush bush : bushes) {
                bush.onDraw(canvas, paint, offsetX, offsetY);
            }
        }
    }

        public static void drawPlayers(Canvas canvas , Paint paint , int offsetX , int offsetY)
        {
            for (Player player:players){
            player.onDraw(canvas , paint , offsetX , offsetY);
        }
    }

    public static void drawShots(Canvas canvas , Paint paint , int offsetX , int offsetY)
    {
        ArrayList<ShotInfo> deletions = new ArrayList<ShotInfo>();
        for (ShotInfo shot:shots){
            double current = System.currentTimeMillis() - startTime;
            if (current>=(shot.getT()+shot.getDuration())) {
                deletions.add(shot);
            } else {
                if (current >= shot.getT() && current < (shot.getT() + shot.getDuration())) {
                    shot.onDraw(canvas, paint, offsetX, offsetY);
                }
            }
        }
        shots.removeAll(deletions);
    }


    public static boolean updateGame(UpdateStatus status)

    {
        updateWalls(status);
        updateBushes(status);
        updatePlayers(status);
        updateTime(status);
        updateShots(status);
        return true;
    }

    public static void updateWalls(UpdateStatus status)
    {
        if (status.getWalls()!=null) {
            walls = status.getWalls();
        }
    }

    public static void updateBushes(UpdateStatus status)
    {
        if (status.getBushes()!=null) {
            bushes = status.getBushes();
        }
    }

    public static  void updatePlayers(UpdateStatus status)
    {
        if (status.getNewPlayers()!=null) {
            players.addAll(status.getNewPlayers());
        }
        if (status.getUpdatePlayers()!=null) {
            for (Player updatePlayer : status.getUpdatePlayers()) {
                for (Player player : players) {
                    if (updatePlayer.getId() == player.getId()) {
                        player.setHealth(updatePlayer.getHealth());
                        player.setX(updatePlayer.getX());
                        player.setY(updatePlayer.getY());
                    }
                }
            }
        }
    }


    public static  void updateTime(UpdateStatus status)
    {
        elapsedTime=status.getElapsedTime();
    }

    // TODO: type conversion
    public static void updateShots(UpdateStatus status)
    {
        if (status.getShots()!=null) {
            shots.addAll(status.getShots());
        }
    }



    public static int getNoPlayer() {
        return noPlayer;
    }

    public static void setNoPlayer(int noPlayer) {
        Game.noPlayer = noPlayer;
    }

    public static double getElapsedTime() {
        return elapsedTime;
    }

    public static void setElapsedTime(double elapsedTime) {
        Game.elapsedTime = elapsedTime;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        Game.players = players;
    }

    public static ArrayList<ShotInfo> getShots() {
        return shots;
    }

    public static void setShots(ArrayList<ShotInfo> shots) {
        Game.shots = shots;
    }

    public static ArrayList<Wall> getWalls() {
        return walls;
    }

    public static void setWalls(ArrayList<Wall> walls) {
        Game.walls = walls;
    }

    public static ArrayList<Bush> getBushes() {
        return bushes;
    }

    public static void setBushes(ArrayList<Bush> bushes) {
        Game.bushes = bushes;
    }

    // interaction with server

    public static UpdateStatus serverAddPlayer()
    {
        // TODO: return start time, needed for shots
        Request request = new Request();
        request.setType(Request.ADDPLAYER);
        UpdateStatus status = request.send();
        return status ;
    }

    public static boolean serverUpdatePosition(Player player)
    {
        Request request=new Request();
        request.setType(Request.UPDATEPOSITION);
        request.addPosition(player.getId(),player.getX() , player.getY());
        request.send();
        return true;
    }

    public static boolean serverUpdateShots(Player player)
    {
        Request request=new Request();
        request.setType(Request.FIRESHOT);
        request.setPlayerId(player.getId());
        request.send();
        return true;
    }

    public static UpdateStatus serverGetUpdates()
    {
        Request request=new Request();
        request.setType(Request.GETUPDATE);
        request.setPlayerId(me.getId());
        UpdateStatus status = request.send();
        return status;
    }

}
