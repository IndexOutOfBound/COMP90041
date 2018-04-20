public class RemoveUser implements NimCommand {
    @Override
    public void excute(PlayerList playerList, String param) {
        if(!playerList.removePlayer(param))
            System.out.println("The player does not exist");
    }

    @Override
    public void excute(PlayerList playerList) {
        System.out.println("Are you sure you want to remove all players? (y/n)");
        boolean yes = Nimsys.chooseYN();
        if(yes)
            playerList.removeAllPlayers();
    }
}
