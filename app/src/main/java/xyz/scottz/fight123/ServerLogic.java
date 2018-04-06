package xyz.scottz.fight123;

import java.util.ArrayList;

/**
 * Created by lei on 2018/3/25.
 */

// TODO: more than one game
public class ServerLogic {
    static int totalPlayers =2 ;
    static ArrayList<ServerPlayer> players;
    static long timeElapsed;
    static ArrayList<ServerWall> walls;
    static ArrayList<ServerBush>bushes;
    static ArrayList<ServerShot> shots;

    // update info for clients
    static ArrayList<ServerPlayerUpdate> updateInfo;


    public static ArrayList<ServerPlayer> getPlayers() {
        return players;
    }


    public static void init()
    {
        players = new ArrayList<ServerPlayer>();
        shots = new ArrayList<ServerShot>();

        updateInfo = new ArrayList<ServerPlayerUpdate>(totalPlayers);
        for (int i=0 ; i<totalPlayers ; i++) {
            updateInfo.add(new ServerPlayerUpdate());
        }
        buildWalls();
        buildBushes();
    }

    public static void buildWalls()
    {
        walls=new ArrayList<ServerWall>();
        ServerWall wall=new ServerWall();
        wall.setX(300);
        wall.setY(500);
        wall.setW(200);
        wall.setH(50);
        walls.add(wall);
    }


    public static void buildBushes()
    {
        bushes=new ArrayList<ServerBush>();
        ServerBush bush=new ServerBush();
        bush.setX(300);
        bush.setY(300);
        bush.setW(200);
        bush.setH(50);
        bushes.add(bush);
    }

    public static int getTotalPlayers() {
        return totalPlayers;
    }

    public static ArrayList<ServerPlayerUpdate> getUpdateInfo() {
        return updateInfo;
    }

    // when a new player joins the game, only happens once per app
    public static Reply onAddPlayer(ServerPlayer player)
    {
        players.add(player);
        player.setId( players.size());
        player.setTeam(player.getId()%2);
        player.setHealth(20);
        player.setX(player.getId()*600+100);
        player.setY(300);

        for (int i=0 ; i<totalPlayers;i++){
            if (i+1!=player.getId()) {
                updateInfo.get(i).addNewPlayer(player);
            }
        }

        Reply result = new Reply();

        result.setId(player.getId());
        result.setTeam(player.getTeam());
        result.setHealth(player.getHealth());
        result.setX(player.getX());
        result.setY(player.getY());
        result.addWalls(walls);
        result.addBushes(bushes);

        return result;
     }

    public static void onUpdatePosition(ServerPlayer player)
    {
        for (ServerPlayer p: players) {
            if (p.id==player.id) {
                p.setX(player.getX());
                p.setY(player.getY());

                for (int i=0 ; i<totalPlayers ; i++) {
                    if (i+1!=player.getId()){
                        updateInfo.get(i).addUpdatePlayer(p);
                    }
                }
            }
        }
    }

    public static void onUpdateShots(ServerPlayer player)
    {
        ServerShot shot = new ServerShot();
        ServerPlayer target = findClosestTarget(player);
        if (target!=null) {
            shot.setFrom(player.getX(), player.getY());
            shot.setTo(target.getX(), target.getY());
            shot.setStartTime(-1);  // initial shot
            shots.add(shot);
        }
    }

    public static int calcDistance(ServerPlayer p1 , ServerPlayer p2)
    {
        int dx = p1.getX()-p2.getX();
        int dy = p1.getY()-p2.getY();
        return dx*dx+dy*dy;
    }

    public static ServerPlayer findClosestTarget(ServerPlayer player)
    {
        int bestDistance = 100000000 ;
        ServerPlayer result = null;
        int targetTeam = 1 - player.getTeam();

        for (ServerPlayer p: players) {
            if (p.getTeam()==targetTeam) {
                int distance = calcDistance(player, p);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    result = p;
                }
            }
        }
        return result;
    }


    public static Reply onRequestUpdate(ServerPlayer player)
    {
        // new players;
        // updated position and health for each player
        // all shots since last update

        Reply result = new Reply() ;

        int id = player.getId()-1;
        result.addNewPlayers(updateInfo.get(id).getNewPlayers());
        result.addUpdatePlayers(updateInfo.get(id).getUpdatePlayers());
        result.addShots(updateInfo.get(id).getShots());
        updateInfo.get(id).reset();

        return result ;
    }

    public static void onTimer()
    {
        ArrayList<ServerShot> deletions = new ArrayList<ServerShot>();
        for (ServerShot shot: shots) {
            shot.onTimer();
            if (shot.isDeleted()) {
                deletions.add(shot);
            }
        }
        shots.removeAll(deletions);
    }
}
