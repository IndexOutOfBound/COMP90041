


public class DisplayUserCommand implements NimCommand {

    @Override
    public void excute(PlayerList playerList, String param) {
        NimPlayer nimPlayer = playerList.getPlayerByUsername(param);
        if( nimPlayer != null)
            System.out.println(nimPlayer.toString());
        else
            System.out.println("The player does not exist");
    }

    @Override
    public void excute(PlayerList playerList) {
        NimPlayer[] players = playerList.getPlayersArray();
        for (NimPlayer nimPlayer: players)
            System.out.println(nimPlayer.toString());
    }

}
