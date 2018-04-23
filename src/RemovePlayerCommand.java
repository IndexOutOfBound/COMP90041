/**
 * @Author: weikai.zeng
 * this class used to remove players
 */



public class RemovePlayerCommand implements NimCommand {

    /**
     * remove one player from player list
     * @param playerList
     * @param param username of player
     */
    @Override
    public void execute(PlayerList playerList, String param) {
        if(!playerList.removePlayer(param))
            System.out.println("The player does not exist.");
    }

    /**
     * remove all players
     * @param playerList
     */
    @Override
    public void execute(PlayerList playerList) {
        System.out.println("Are you sure you want to remove all players? (y/n)");
        boolean yes = Nimsys.chooseYN();
        if(yes)
            playerList.removeAllPlayers();
    }
}
