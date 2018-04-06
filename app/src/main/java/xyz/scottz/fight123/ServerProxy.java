package xyz.scottz.fight123;

/**
 * Created by lei on 2018/3/29.
 */

public class ServerProxy {

    public static Reply send(Request request){
     switch (request.getType()) {
         case Request.ADDPLAYER: return onAddPlayer(request);
         case Request.UPDATEPOSITION: return onUpdatePosition(request);
         case Request.FIRESHOT: return onFireShot(request);
         case Request.GETUPDATE: return onGetUpdate(request);
         default: return null;
     }
    }

    public  static Reply onAddPlayer(Request request)
    {
        ServerPlayer player = new ServerPlayer();
        return ServerLogic.onAddPlayer(player);
    }

    public static Reply onUpdatePosition(Request request)
    {
        ServerPlayer player = new ServerPlayer();
        player.setId(request.getPlayerId());
        player.setX(request.getPosX());
        player.setY(request.getPosY());
        ServerLogic.onUpdatePosition(player);
        return null;
    }

    public static Reply onFireShot(Request request)
    {
        for (ServerPlayer player:ServerLogic.getPlayers()) {
            if (player.getId() == request.getPlayerId()) {
                ServerLogic.onUpdateShots(player);
                break;
            }
        }
        return null;
    }

    public static Reply onGetUpdate(Request request)
    {
        for (ServerPlayer player:ServerLogic.getPlayers()) {
            if (player.getId() == request.getPlayerId()) {
                return ServerLogic.onRequestUpdate(player);
            }
        }
        return null;
    }


}
