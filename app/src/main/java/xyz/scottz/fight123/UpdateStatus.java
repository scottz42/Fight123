package xyz.scottz.fight123;

import java.util.ArrayList;

/**
 * Created by lei on 2018/3/25.
 */

public class UpdateStatus {
    double elapsedTime;
    ArrayList<Player> newPlayers,updatePlayers;
    ArrayList<Wall> walls;
    ArrayList<Bush> bushes;
    ArrayList<ShotInfo> shots;

    UpdateStatus()
    {
        newPlayers=null;
        updatePlayers=null;
        walls=null;
        bushes=null;
        shots=null;
    }

    public ArrayList<Player> getNewPlayers() {
        return newPlayers;
    }

    public void setNewPlayers(ArrayList<Player> newPlayers) {
        this.newPlayers = newPlayers;
    }

    public ArrayList<Player> getUpdatePlayers() {
        return updatePlayers;
    }

    public void setUpdatePlayers(ArrayList<Player> updatePlayers) {
        this.updatePlayers = updatePlayers;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public ArrayList<Bush> getBushes() {
        return bushes;
    }

    public void setBushes(ArrayList<Bush> bushes) {
        this.bushes = bushes;
    }

    public ArrayList<ShotInfo> getShots() {
        return shots;
    }

    public void setShots(ArrayList<ShotInfo> shots) {
        this.shots = shots;
    }



    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
