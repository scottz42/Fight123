package xyz.scottz.fight123;

import java.util.ArrayList;

/**
 * Created by lei on 2018/3/29.
 */

public class Request {
    final static int ADDPLAYER =1 ;
    final static int UPDATEPOSITION =2 ;
    final static int FIRESHOT= 3;
    final static int GETUPDATE =4 ;

    int type;
    int posX , posY ;
    int playerId;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void addPosition(int id, int x , int y)
    {
        playerId=id;
        posX = x ;
        posY = y ;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public UpdateStatus send()
    {
        UpdateStatus status = convertReply(ServerProxy.send(this));
        return status;
    }

    public UpdateStatus convertReply(Reply reply)
    {
        if (reply==null) {
            return null;
        }

        UpdateStatus status = new UpdateStatus();

        // player info from add player
        if (reply.id>=0) {
            Player player = new Player(reply.getHealth(),reply.getX(),reply.getY());
            player.setId(reply.getId());
            player.setTeam(reply.getTeam());
            status.setNewPlayers(new ArrayList<Player>());
            status.getNewPlayers().add(player);
        }

        // walls & bushes
        if (reply.getWalls()!=null) {
            status.setWalls(new ArrayList<Wall>());
            for (ReplyRect rect: reply.getWalls()) {
                Wall wall = new Wall(rect.getX(),rect.getY(),rect.getH(),rect.getW());
                status.getWalls().add(wall);
            }
        }

        if (reply.getBushes()!=null) {
            status.setBushes(new ArrayList<Bush>());
            for (ReplyRect rect: reply.getBushes()) {
                Bush bush = new Bush(rect.getX(),rect.getY(),rect.getH(),rect.getW());
                status.getBushes().add(bush);
            }
        }

        // new players , update players
        if (reply.getNewPlayers()!=null)
        {
            status.setNewPlayers(new ArrayList<Player>());
            for (ReplyPlayer player: reply.getNewPlayers())
            {
                Player newPlayer=new Player(player.getHealth(),player.getX(),player.getY());
                newPlayer.setId((player.getId()));
                status.getNewPlayers().add(newPlayer);
            }

        }

        if (reply.getUpdatePlayers()!=null)
        {
            status.setUpdatePlayers(new ArrayList<Player>());
            for (ReplyPlayer player: reply.getUpdatePlayers())
            {
                Player newPlayer=new Player(player.getHealth(),player.getX(),player.getY());
                newPlayer.setId(player.getId());    // TODO: put into constructor
                status.getUpdatePlayers().add(newPlayer);
            }

        }

        // shots ;
        if (reply.getShots()!=null)
        {
            status.setShots(new ArrayList<ShotInfo>());
            for (ReplyShot replyShot: reply.getShots())
            {
                ShotInfo shot=new ShotInfo(replyShot.getType(),replyShot.getX(),replyShot.getY(),replyShot.getT(),replyShot.getDuration());
                status.getShots().add(shot);

            }
        }
        return status ;

    }
}
