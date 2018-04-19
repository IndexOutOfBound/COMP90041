public class PlayerList {

    private static int initialSizeOfArray = 100;

    private NimPlayer[] players = new NimPlayer[initialSizeOfArray];

    private int indexOfEnd = 0;


    public void addPlayer(NimPlayer nimPlayer){
        if(!userExist(nimPlayer.getUserName())){
            if(indexOfEnd == players.length - 1) {
                NimPlayer[] temp = players;
                players = new NimPlayer[players.length * 2];
                System.arraycopy(temp, 0 ,players, 0, indexOfEnd + 1);
            }
            indexOfEnd++;
            players[indexOfEnd] = nimPlayer;
        } else{
            System.out.println("");
        }
    }

    public boolean userExist(String userName){
        if(getTheIndexOfPlayer(userName) == -1)
            return false;
        else
            return true;
    }

    public NimPlayer getPlayerByUsername(String userName){
        int index = getTheIndexOfPlayer(userName);
        if(index == -1)
            return null;

        return players[index];
    }

    /**
     *
     * @param userName the user name of player
     * @return the index of player
     * if the user not exist return -1
     */
    private int getTheIndexOfPlayer(String userName){
        for(int i =0 ; i<=indexOfEnd; i++){
            if(userName.compareTo(players[i].getUserName()) == 0)
                return i;
        }

        return -1;
    }


}
