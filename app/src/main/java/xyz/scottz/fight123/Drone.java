package xyz.scottz.fight123;

public class Drone
{

    static int id;

    public static void serverAddDrone()
    {
        Request request = new Request();
        request.setType(Request.ADDPLAYER);
        UpdateStatus status = request.send();
        id = status.getNewPlayers().get(0).getId();
    }

    public  static void onTimer()
    {

        Request request=new Request();
        request.setType(Request.GETUPDATE);
        request.setPlayerId(id );
        UpdateStatus status = request.send();
    }















}
