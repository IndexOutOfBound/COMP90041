

public class RankCommand implements Command{
    @Override
    public void excute(PlayerList playerList, String param) {
        int dirc = -1;
        if(param.compareToIgnoreCase("desc") == 0)
            dirc = 0;

        if(param.compareToIgnoreCase("asc") == 0)
            dirc = 1;

        if(dirc != -1) {// the input is correct
            NimPlayer[] sortedPlayer = playerList.sort(dirc);

            for (NimPlayer player : sortedPlayer) {
                System.out.println(player.toRankString());
            }
        } else {
            printHelp();
        }
    }

    @Override
    public void excute(PlayerList playerList) {
        excute(playerList, "asc");
    }

    @Override
    public void printHelp() {

    }
}
