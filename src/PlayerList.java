
/**
 * @Author: weikai.zeng
 * this class used to represent a list of player
 */

public class PlayerList {

    private static int INITIAL_SIZE_OF_ARRAY = 100;

    private NimPlayer[] players = new NimPlayer[INITIAL_SIZE_OF_ARRAY];

    private int numberOfPlayers = 0;


    /**
     * add player into player list
     * @param nimPlayer
     */
    public void addPlayer(NimPlayer nimPlayer){
        if(!userExist(nimPlayer.getUserName())){
            if(numberOfPlayers == players.length) {//when the array is full, double the size of it
                NimPlayer[] temp = players;
                players = new NimPlayer[players.length * 2];
                System.arraycopy(temp, 0 ,players, 0, numberOfPlayers);
            }
            players[numberOfPlayers] = nimPlayer;
            numberOfPlayers++;
        }
    }

    /**
     * remove one player from player list
     * @param userName the username of user which you want to remove
     * @return one boolean value indicate whether the player exist
     *      true: exist
     *      false: noe exist
     */
    public boolean removePlayer(String userName){
        int index = getTheIndexOfPlayer(userName);
        if(index == -1)
            return  false;

        for(int i = index; i< numberOfPlayers; i++)
            players[i] = players[i+1];

        numberOfPlayers--;
        return true;
    }

    /**
     *remove all the player in player list
     */
    public void removeAllPlayers(){
        this.players = new NimPlayer[INITIAL_SIZE_OF_ARRAY];
        this.numberOfPlayers = 0;
    }

    /**
     * sort all players by the winrate
     * @param dirc indicate the direction of sequence, 1 stand for asc, -1 stand for desc
     * @return the sorted players
     */
    public NimPlayer[] sortByWinRate(int dirc){
        NimPlayer[] sortedPlayers = new NimPlayer[numberOfPlayers];
        System.arraycopy(players, 0 ,sortedPlayers, 0, numberOfPlayers);
        for(int i = 1; i < numberOfPlayers; i++){
            NimPlayer temp = sortedPlayers[i];
            int j = i - 1;
            while( j >=0 &&
                    ( temp.compareByWinrate(sortedPlayers[j]) == dirc
                       || (temp.compareByWinrate(sortedPlayers[j]) == 0 && temp.compareByUserName(sortedPlayers[j]) < 0))){
                    sortedPlayers[j + 1] = sortedPlayers[j];
                    j--;
            }
            sortedPlayers[j+1] = temp;
        }
        return sortedPlayers;
    }


    /**
     * sort all players by the username
     * @return the sorted players
     */
    public NimPlayer[] sortByUserName(){
        NimPlayer[] sortedPlayers = new NimPlayer[numberOfPlayers];
        System.arraycopy(players, 0 ,sortedPlayers, 0, numberOfPlayers);
        for(int i = 1; i < numberOfPlayers; i++){
            NimPlayer temp = sortedPlayers[i];
            int j = i - 1;
            while( j >=0 && temp.compareByUserName(sortedPlayers[j]) < 0){
                sortedPlayers[j + 1] = sortedPlayers[j];
                j--;
            }
            sortedPlayers[j+1] = temp;
        }
        return sortedPlayers;
    }

    /**
     * test did one user exist
     * @param userName
     * @return
     */
    private boolean userExist(String userName){
        return getTheIndexOfPlayer(userName) == 1;
    }

    /**
     *
     * @param userName
     * @return the NimPlayer
     * return null if the player not exist
     */
    public NimPlayer getPlayerByUsername(String userName) {
        int index = getTheIndexOfPlayer(userName);
        if(index == -1)
            return null;

        return players[index];
    }

    /**
     * @return the array of all players
     */
    public NimPlayer[] getPlayersArray() {
        NimPlayer[] players = new NimPlayer[numberOfPlayers];
        System.arraycopy(this.players, 0 ,players, 0, numberOfPlayers);
        return players;
    }

    /**
     *
     * @param userName the user name of player
     * @return the index of player
     * if the user not exist return -1
     */
    private int getTheIndexOfPlayer(String userName){
        for(int i = 0; i< numberOfPlayers; i++){
            if(userName.compareTo(players[i].getUserName()) == 0)
                return i;
        }

        return -1;
    }


}
