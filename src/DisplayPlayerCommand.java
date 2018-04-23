/**
 * @Author: weikai.zeng
 * this class used for display player
 */


public class DisplayPlayerCommand implements NimCommand {

    /**
     * display the player according to the username
     * @param playerList
     * @param param
     */
    @Override
    public void execute(PlayerList playerList, String param) {
        NimPlayer nimPlayer = playerList.getPlayerByUsername(param);
        if( nimPlayer != null)//when the player exist
            System.out.println(nimPlayer.toString());
        else
            System.out.println("The player does not exist.");
    }


    /**
     * display all players
     * @param playerList
     */
    @Override
    public void execute(PlayerList playerList) {
        NimPlayer[] players = playerList.sortByUserName();
        for (NimPlayer nimPlayer: players)
            System.out.println(nimPlayer.toString());
    }

}
