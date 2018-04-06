package xyz.scottz.fight123;

import java.util.ArrayList;

/**
 * Created by lei on 2018/3/29.
 */

public class ServerPlayerUpdate {
    ArrayList<ServerPlayer> newPlayers , updatePlayers ;
    ArrayList<ServerShot> shots ;

    ServerPlayerUpdate()
    {
        newPlayers = new ArrayList<ServerPlayer>();
        updatePlayers = new ArrayList<ServerPlayer>();
        shots = new ArrayList<ServerShot>();
    }

    public void addNewPlayer(ServerPlayer player)
    {
        newPlayers.add(player);
    }

    public void addUpdatePlayer(ServerPlayer player)
    {
        updatePlayers.add(player);
    }

    public void addShot(ServerShot shot)
    {
        shots.add(shot);
    }

    public ArrayList<ServerPlayer> getNewPlayers() {
        return newPlayers;
    }

    public void setNewPlayer(ArrayList<ServerPlayer> newPlayer) {
        this.newPlayers = newPlayer;
    }

    public ArrayList<ServerPlayer> getUpdatePlayers() {
        return updatePlayers;
    }

    public void setUpdatePlayers(ArrayList<ServerPlayer> updatePlayers) {
        this.updatePlayers = updatePlayers;
    }

    public ArrayList<ServerShot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<ServerShot> shots) {
        this.shots = shots;
    }

    public void reset()
    {
        newPlayers.clear();
        updatePlayers.clear();
        shots.clear();
    }
}
