
/**
 * @Author: weikai.zeng
 * this class used to rank all players
 */

public class RankCommand implements NimCommand {

    /**
     * rank all player according to the order direction user inputted
     * @param playerList
     * @param param the sorting direction of players, just 'asc' and 'desc' are accepted
     */
    @Override
    public void execute(PlayerList playerList, String param) {
        int dirc = 0;

        if(param.compareToIgnoreCase("desc") == 0)
            dirc = 1;

        if(param.compareToIgnoreCase("asc") == 0)
            dirc = -1;

        if(dirc != 0) {// the input is correct
            NimPlayer[] sortedPlayer = playerList.sort(dirc);

            for (NimPlayer player : sortedPlayer) {
                 System.out.println(player.toRankString());
            }
        } else {
            System.out.println("Invalid input, please input 'asc' or 'desc'");
        }
    }

    /**
     * rank all player as desc order
     * @param playerList
     */
    @Override
    public void execute(PlayerList playerList) {
        execute(playerList, "desc");
    }
}
