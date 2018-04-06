package xyz.scottz.fight123;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lei on 2018/3/29.
 */

public class Reply {
    int id, team, x, y, health;
    ArrayList<ReplyRect> walls, bushes;
    ArrayList<ReplyPlayer> newPlayers, updatePlayers;
    ArrayList<ReplyShot> shots;

    Reply() {
        id = -1;
        walls = null;
        bushes = null;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList<ReplyRect> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<ReplyRect> walls) {
        this.walls = walls;
    }

    public ArrayList<ReplyRect> getBushes() {
        return bushes;
    }

    public void setBushes(ArrayList<ReplyRect> bushes) {
        this.bushes = bushes;
    }

    public ArrayList<ReplyPlayer> getNewPlayers() {
        return newPlayers;
    }

    public void setNewPlayers(ArrayList<ReplyPlayer> newPlayers) {
        this.newPlayers = newPlayers;
    }

    public ArrayList<ReplyPlayer> getUpdatePlayers() {
        return updatePlayers;
    }

    public void setUpdatePlayers(ArrayList<ReplyPlayer> updatePlayers) {
        this.updatePlayers = updatePlayers;
    }

    public ArrayList<ReplyShot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<ReplyShot> shots) {
        this.shots = shots;
    }

    public void addWalls(ArrayList<ServerWall> walls) {
        this.walls = new ArrayList<ReplyRect>();
        for (ServerWall wall : walls) {
            ReplyRect rect = new ReplyRect(wall.getX(), wall.getY(), wall.getH(), wall.getW());
            this.walls.add(rect);
        }
    }

    public void addBushes(ArrayList<ServerBush> bushs) {
        this.bushes = new ArrayList<ReplyRect>();
        for (ServerBush bush : bushs) {
            ReplyRect rect = new ReplyRect(bush.getX(), bush.getY(), bush.getH(), bush.getW());
            this.bushes.add(rect);
        }
    }

    public void addNewPlayers(ArrayList<ServerPlayer> players)
    {
        newPlayers = new ArrayList<ReplyPlayer>();
        for (ServerPlayer player:players) {
            newPlayers.add(new ReplyPlayer(player.getId(),player.getX(),player.getY(),player.getHealth()));
        }
    }


    public void addUpdatePlayers(ArrayList<ServerPlayer> players)
    {
        updatePlayers = new ArrayList<ReplyPlayer>();
        for (ServerPlayer player:players) {
            updatePlayers.add(new ReplyPlayer(player.getId(),player.getX(),player.getY(),player.getHealth()));
        }
    }

    public void addShots(ArrayList<ServerShot> newShots)
    {
        shots = new ArrayList<ReplyShot>();
        for (ServerShot shot: newShots) {
            //Log.d(null,"Reply:addShots: shot position:"+shot.getX()+","+shot.getY());
            // TODO: duration
            shots.add(new ReplyShot(shot.getType() , shot.getX() , shot.getY() , shot.getLastShotTime(),shot.getShotDuration()+50)) ;
        }
    }
}
