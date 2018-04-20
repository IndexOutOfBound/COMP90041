public class ResetCommand implements NimCommand {
    @Override
    public void excute(PlayerList playerList, String param) {
        NimPlayer nimPlayer = playerList.getPlayerByUsername(param);
        if(nimPlayer != null){
            nimPlayer.setNumberOfGames(Nimsys.ZERO);
            nimPlayer.setWinGames(Nimsys.ZERO);
        } else{
            System.out.println("The player does not exist.");
        }
    }

    @Override
    public void excute(PlayerList playerList) {

        System.out.println("Are you sure you want to reset all player statistics? (y/n)");
        boolean yes = Nimsys.chooseYN();
        if(yes) {
            NimPlayer[] nimPlayers = playerList.getPlayersArray();
            for(NimPlayer nimPlayer: nimPlayers){
                nimPlayer.setWinGames(Nimsys.ZERO);
                nimPlayer.setNumberOfGames(Nimsys.ZERO);
            }
        }
    }
}
